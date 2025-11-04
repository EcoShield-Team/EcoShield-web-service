package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.response.DeteccionResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.GeminiResponseDTO;
import com.api.ecoshieldwebservice.entities.*;
import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.PlagaTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import com.api.ecoshieldwebservice.interfaces.ICloudinaryService;
import com.api.ecoshieldwebservice.interfaces.IDeteccionService;
import com.api.ecoshieldwebservice.interfaces.IGeminiService;
import com.api.ecoshieldwebservice.repositories.*;
import com.api.ecoshieldwebservice.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Service
public class DeteccionService implements IDeteccionService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private FotoRepository fotoRepository;
    @Autowired private DeteccionRepository deteccionRepository;
    @Autowired private EnfermedadRepository enfermedadRepository;
    @Autowired private PlagaRepository plagaRepository;
    @Autowired private IGeminiService geminiService;
    @Autowired private ICloudinaryService cloudinaryService;

    @Override
    public DeteccionResponseDTO analizarCultivo(MultipartFile imagen, String correoUsuario) {
        if (imagen == null || imagen.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe enviar una imagen válida");
        }

        if (ImageUtils.isBlurry(imagen)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen ha salido en movimiento.");
        }

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correoUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        String url = cloudinaryService.uploadImage(imagen);

        Foto foto = new Foto();
        foto.setUsuario(usuario);
        foto.setFotoRuta(url);
        fotoRepository.save(foto);

        GeminiResponseDTO aiResult = geminiService.analyze(imagen);

        Deteccion deteccion = new Deteccion();
        deteccion.setFoto(foto);
        deteccion.setDeteccionResultado(aiResult.getDescripcion());
        deteccion.setConfianza(aiResult.getConfianza());
        deteccion.setRegionX(aiResult.getX());
        deteccion.setRegionY(aiResult.getY());
        deteccion.setRegionAncho(aiResult.getAncho());
        deteccion.setRegionAlto(aiResult.getAlto());

        asignarEntidadDetectada(deteccion, aiResult);

        // Si no se pudo mapear a una ficha, guardamos lo que vino de IA en la detección
        if (deteccion.getEnfermedad() == null && deteccion.getPlaga() == null) {
            deteccion.setSintomasIA(aiResult.getSintomas());
            deteccion.setTratamientoIA(aiResult.getTratamiento());
            deteccion.setCausasIA(aiResult.getCausas());
            deteccion.setPrevencionIA(aiResult.getPrevencion());
        }

        deteccionRepository.save(deteccion);
        return convertirADTO(deteccion, aiResult);
    }

    @Override
    public DeteccionResponseDTO obtenerResultado(Long id, String correoUsuario) {
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correoUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Deteccion deteccion = deteccionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detección no encontrada"));

        if (!deteccion.getFoto().getUsuario().getUsuarioId().equals(usuario.getUsuarioId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para ver esta detección");
        }

        return convertirADTO(deteccion, null);
    }

    @Override
    public List<DeteccionResponseDTO> listarHistorial(String correoUsuario) {
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correoUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        return deteccionRepository.findByFoto_UsuarioOrderByDeteccionFechaDesc(usuario)
                .stream()
                .map(d -> convertirADTO(d, null))
                .toList();
    }

    // -------------------- NÚCLEO: Vincular a ficha existente o crear nueva con enums correctos --------------------

    private void asignarEntidadDetectada(Deteccion deteccion, GeminiResponseDTO ai) {
        String nombre = ai.getNombre();
        if (nombre == null) return;

        String nombreNormalizado = norm(nombre);

        if ("PLAGA".equalsIgnoreCase(ai.getTipo())) {
            Optional<Plaga> plagaExistente = plagaRepository.findAll().stream()
                    .filter(p -> p.getPlagaNombre() != null && norm(p.getPlagaNombre()).equals(nombreNormalizado))
                    .findFirst();

            if (plagaExistente.isPresent()) {
                deteccion.setPlaga(plagaExistente.get());
            } else {
                Plaga p = new Plaga();
                p.setPlagaNombre(ai.getNombre());
                p.setPlagaNombreCientifico(ai.getNombreCientifico() != null ? ai.getNombreCientifico() : "Desconocido");
                p.setPlagaDescripcion(ai.getDescripcion());
                if (deteccion.getFoto() != null && deteccion.getFoto().getFotoRuta() != null) {
                    p.setPlagaFoto(deteccion.getFoto().getFotoRuta());
                }

                // enums ↴
                p.setPlagaTipo(toPlagaTipo(ai.getTipoPlaga()));
                p.setSeveridad(toSeveridad(ai.getSeveridad()));
                p.setTemporada(toTemporada(ai.getTemporada()));

                // textos ↴
                p.setPlagaSintomas(ai.getSintomas());
                p.setPlagaTratamiento(ai.getTratamiento());
                p.setPlagaCausas(ai.getCausas());
                p.setPlagaPrevenciones(ai.getPrevencion());

                plagaRepository.save(p);
                deteccion.setPlaga(p);
            }

        } else if ("ENFERMEDAD".equalsIgnoreCase(ai.getTipo())) {
            Optional<Enfermedad> enfExistente = enfermedadRepository.findAll().stream()
                    .filter(e -> e.getEnfermedadNombre() != null && norm(e.getEnfermedadNombre()).equals(nombreNormalizado))
                    .findFirst();

            if (enfExistente.isPresent()) {
                deteccion.setEnfermedad(enfExistente.get());
            } else {
                Enfermedad e = new Enfermedad();
                e.setEnfermedadNombre(ai.getNombre());
                e.setEnfermedadNombreCientifico(ai.getNombreCientifico() != null ? ai.getNombreCientifico() : "Desconocido");
                e.setEnfermedadDescripcion(ai.getDescripcion());
                if (deteccion.getFoto() != null && deteccion.getFoto().getFotoRuta() != null) {
                    e.setEnfermedadFoto(deteccion.getFoto().getFotoRuta());
                }


                // enums ↴
                e.setEnfermedadTipo(toEnfermedadTipo(/* puedes crear otro campo en DTO; por ahora inferimos de texto */ ai.getTipoPlaga()));
                e.setSeveridad(toSeveridad(ai.getSeveridad()));
                e.setTemporada(toTemporada(ai.getTemporada()));

                // textos ↴
                e.setEnfermedadSintomas(ai.getSintomas());
                e.setEnfermedadTratamiento(ai.getTratamiento());
                e.setEnfermedadCausas(ai.getCausas());
                e.setEnfermedadPrevenciones(ai.getPrevencion());

                enfermedadRepository.save(e);
                deteccion.setEnfermedad(e);
            }
        }
    }

    // -------------------- CONVERSORES A ENUMS (sin strings) --------------------

    private Severidad toSeveridad(String s) {
        if (s == null) return Severidad.LEVE;
        String v = norm(s);
        if (v.contains("moder")) return Severidad.MODERADA;
        if (v.contains("grave") || v.contains("sever")) return Severidad.GRAVE;
        return Severidad.LEVE;
    }

    private Temporada toTemporada(String s) {
        if (s == null) return Temporada.TODO_EL_AÑO;
        String v = norm(s);
        if (v.contains("primavera")) return Temporada.PRIMAVERA;
        if (v.contains("verano")) return Temporada.VERANO;
        if (v.contains("oton")) return Temporada.OTOÑO; // "otoño" sin tilde normalizado → "oton"
        if (v.contains("invierno")) return Temporada.INVIERNO;
        return Temporada.TODO_EL_AÑO;
    }

    private PlagaTipo toPlagaTipo(String s) {
        if (s == null) return PlagaTipo.OTRO;
        String v = norm(s);
        if (v.contains("insect")) return PlagaTipo.INSECTO;
        if (v.contains("acaro")) return PlagaTipo.ACARO;
        if (v.contains("nemat")) return PlagaTipo.NEMATODO;
        return PlagaTipo.OTRO;
    }

    private EnfermedadTipo toEnfermedadTipo(String s) {
        if (s == null) return EnfermedadTipo.OTRO;
        String v = norm(s);
        if (v.contains("hongo") || v.contains("fung")) return EnfermedadTipo.HONGO;
        if (v.contains("bacter")) return EnfermedadTipo.BACTERIA;
        if (v.contains("virus")) return EnfermedadTipo.VIRUS;
        if (v.contains("nemat")) return EnfermedadTipo.NEMATODO;
        return EnfermedadTipo.OTRO;
    }

    // Normaliza: minúsculas, sin tildes/diacríticos y sin espacios extra
    private String norm(String in) {
        String s = in == null ? "" : in.trim().toLowerCase();
        s = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}+", ""); // quita tildes
        return s;
    }

    // -------------------- DTO salida --------------------

    private DeteccionResponseDTO convertirADTO(Deteccion d, GeminiResponseDTO aiResult) {
        DeteccionResponseDTO dto = new DeteccionResponseDTO();
        dto.setDeteccionId(d.getDeteccionId());
        dto.setFotoUrl(d.getFoto().getFotoRuta());
        dto.setDescripcion(d.getDeteccionResultado());
        dto.setConfianza(d.getConfianza());
        dto.setFecha(d.getDeteccionFecha());
        dto.setCoordenadas(new DeteccionResponseDTO.RegionDTO(
                d.getRegionX(), d.getRegionY(), d.getRegionAncho(), d.getRegionAlto()
        ));

        if (d.getEnfermedad() != null) {
            Enfermedad e = d.getEnfermedad();
            dto.setFichaId(e.getEnfermedadId());
            dto.setTipoFicha("ENFERMEDAD");
            dto.setTipo("ENFERMEDAD");
            dto.setNombreDetectado(e.getEnfermedadNombre());
            dto.setSintomas(e.getEnfermedadSintomas());
            dto.setTratamiento(e.getEnfermedadTratamiento());
            dto.setCausas(e.getEnfermedadCausas());
            dto.setPrevencion(e.getEnfermedadPrevenciones());

        } else if (d.getPlaga() != null) {
            Plaga p = d.getPlaga();
            dto.setFichaId(p.getPlagaId());
            dto.setTipoFicha("PLAGA");
            dto.setTipo("PLAGA");
            dto.setNombreDetectado(p.getPlagaNombre());
            dto.setSintomas(p.getPlagaSintomas());
            dto.setTratamiento(p.getPlagaTratamiento());
            dto.setCausas(p.getPlagaCausas());
            dto.setPrevencion(p.getPlagaPrevenciones());

        } else {
            // fallback IA
            dto.setTipo("IA");
            dto.setNombreDetectado(aiResult != null ? aiResult.getNombre() : "No clasificado");
            dto.setSintomas(aiResult != null ? aiResult.getSintomas() : "Información no disponible.");
            dto.setTratamiento(aiResult != null ? aiResult.getTratamiento() : "Información no disponible.");
            dto.setCausas(aiResult != null ? aiResult.getCausas() : "Información no disponible.");
            dto.setPrevencion(aiResult != null ? aiResult.getPrevencion() : "Información no disponible.");
        }

        return dto;
    }
}

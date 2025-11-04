package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.response.DeteccionResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.GeminiResponseDTO;
import com.api.ecoshieldwebservice.entities.*;
import com.api.ecoshieldwebservice.interfaces.ICloudinaryService;
import com.api.ecoshieldwebservice.interfaces.IDeteccionService;
import com.api.ecoshieldwebservice.interfaces.IGeminiService;
import com.api.ecoshieldwebservice.repositories.*;
import com.api.ecoshieldwebservice.util.TextSimilarityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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

    // -----------------------------
    // MÉTODOS AUXILIARES PRIVADOS
    // -----------------------------

    private void asignarEntidadDetectada(Deteccion deteccion, GeminiResponseDTO aiResult) {
        String nombre = aiResult.getNombre();
        if (nombre == null) return;

        if ("PLAGA".equalsIgnoreCase(aiResult.getTipo())) {
            Optional<Plaga> plaga = plagaRepository.findByPlagaNombreIgnoreCase(nombre)
                    .or(() -> plagaRepository.findByAliasIgnoreCase(nombre))
                    .or(() -> buscarPlagaSimilar(nombre));
            deteccion.setPlaga(plaga.orElse(null));

        } else if ("ENFERMEDAD".equalsIgnoreCase(aiResult.getTipo())) {
            Optional<Enfermedad> enf = enfermedadRepository.findByEnfermedadNombreIgnoreCase(nombre)
                    .or(() -> enfermedadRepository.findByAliasIgnoreCase(nombre))
                    .or(() -> buscarEnfermedadSimilar(nombre));
            deteccion.setEnfermedad(enf.orElse(null));
        }
    }

    private DeteccionResponseDTO convertirADTO(Deteccion d, GeminiResponseDTO aiResult) {
        DeteccionResponseDTO dto = new DeteccionResponseDTO();
        dto.setDeteccionId(d.getDeteccionId());
        dto.setFotoUrl(d.getFoto().getFotoRuta());
        dto.setDescripcion(d.getDeteccionResultado());
        dto.setConfianza(d.getConfianza());
        dto.setFecha(d.getDeteccionFecha());
        dto.setCoordenadas(new DeteccionResponseDTO.RegionDTO(d.getRegionX(), d.getRegionY(), d.getRegionAncho(), d.getRegionAlto()));

        if (d.getEnfermedad() != null) {
            dto.setFichaId(d.getEnfermedad().getEnfermedadId());
            dto.setTipoFicha("ENFERMEDAD");
            dto.setTipo("ENFERMEDAD");
            dto.setNombreDetectado(d.getEnfermedad().getEnfermedadNombre());
        } else if (d.getPlaga() != null) {
            dto.setFichaId(d.getPlaga().getPlagaId());
            dto.setTipoFicha("PLAGA");
            dto.setTipo("PLAGA");
            dto.setNombreDetectado(d.getPlaga().getPlagaNombre());
        } else {
            dto.setTipo("IA");
            dto.setNombreDetectado(aiResult != null ? aiResult.getNombre() : "No clasificado");
        }

        return dto;
    }

    private Optional<Enfermedad> buscarEnfermedadSimilar(String nombreIA) {
        return enfermedadRepository.findAll().stream()
                .filter(e -> TextSimilarityUtils.areSimilar(nombreIA, e.getEnfermedadNombre(), 3))
                .findFirst();
    }

    private Optional<Plaga> buscarPlagaSimilar(String nombreIA) {
        return plagaRepository.findAll().stream()
                .filter(p -> TextSimilarityUtils.areSimilar(nombreIA, p.getPlagaNombre(), 3))
                .findFirst();
    }
}

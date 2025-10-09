package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.response.DeteccionResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.GeminiResponseDTO;
import com.api.ecoshieldwebservice.entities.*;
import com.api.ecoshieldwebservice.interfaces.ICloudinaryService;
import com.api.ecoshieldwebservice.interfaces.IDeteccionService;
import com.api.ecoshieldwebservice.interfaces.IGeminiService;
import com.api.ecoshieldwebservice.repositories.DeteccionRepository;
import com.api.ecoshieldwebservice.repositories.EnfermedadRepository;
import com.api.ecoshieldwebservice.repositories.FotoRepository;
import com.api.ecoshieldwebservice.repositories.PlagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class DeteccionService implements IDeteccionService {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private DeteccionRepository deteccionRepository;

    @Autowired
    private EnfermedadRepository  enfermedadRepository;

    @Autowired
    private PlagaRepository  plagaRepository;

    @Autowired
    private IGeminiService geminiService;

    @Autowired
    private ICloudinaryService cloudinaryService;

    @Override
    public DeteccionResponseDTO analizarCultivo(MultipartFile imagen, Usuario usuario) {
        try {
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

            if ("PLAGA".equalsIgnoreCase(aiResult.getTipo()) && aiResult.getNombre() != null) {
                Plaga plaga = plagaRepository.findByPlagaNombreIgnoreCase(aiResult.getNombre())
                        .orElse(null);
                deteccion.setPlaga(plaga);
            } else if ("ENFERMEDAD".equalsIgnoreCase(aiResult.getTipo()) && aiResult.getNombre() != null) {
                Enfermedad enfermedad = enfermedadRepository.findByEnfermedadNombreIgnoreCase(aiResult.getNombre())
                        .orElse(null);
                deteccion.setEnfermedad(enfermedad);
            }

            deteccionRepository.save(deteccion);

            DeteccionResponseDTO.RegionDTO region = new DeteccionResponseDTO.RegionDTO(
                    aiResult.getX(), aiResult.getY(), aiResult.getAncho(), aiResult.getAlto()
            );

            DeteccionResponseDTO dto = new DeteccionResponseDTO();
            dto.setDeteccionId(deteccion.getDeteccionId());
            dto.setFotoUrl(foto.getFotoRuta());
            dto.setDescripcion(aiResult.getDescripcion());
            dto.setConfianza(deteccion.getConfianza());
            dto.setTipo(aiResult.getTipo());
            dto.setNombreDetectado(aiResult.getNombre());
            dto.setCoordenadas(region);
            dto.setFecha(deteccion.getDeteccionFecha());

            return dto;

        } catch (Exception e) {
            throw new RuntimeException("Error al analizar la imagen con IA: " + e.getMessage(), e);
        }
    }
}

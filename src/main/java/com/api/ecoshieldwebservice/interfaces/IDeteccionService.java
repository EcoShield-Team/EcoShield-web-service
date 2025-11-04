package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.response.DeteccionResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDeteccionService {
    DeteccionResponseDTO analizarCultivo(MultipartFile imagen, String correoUsuario);
    DeteccionResponseDTO obtenerResultado(Long id, String correoUsuario);
    List<DeteccionResponseDTO> listarHistorial(String correoUsuario);
}

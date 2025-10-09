package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.response.DeteccionResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.web.multipart.MultipartFile;

public interface IDeteccionService {
    DeteccionResponseDTO analizarCultivo(MultipartFile imagen, Usuario usuario);
}

package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.*;
import com.api.ecoshieldwebservice.dtos.response.UsuarioResponseDTO;

import java.util.List;

public interface IUsuarioServices {
    UsuarioProfileDTO findById(Long id);
    UsuarioProfileDTO updateProfile(Long id, UsuarioProfileDTO usuarioProfileDTO);
    List<UsuarioResponseDTO> findAll();
}

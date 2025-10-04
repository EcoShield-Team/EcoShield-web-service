package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.user.UsuarioProfileDTO;
import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.request.UsuarioUpdateDTO;

import java.util.List;

public interface IUsuarioService {
    UsuarioProfileDTO findById(Long id);
    UsuarioProfileDTO updateProfile(Long id, UsuarioUpdateDTO dto);
    List<UsuarioResponseDTO> findAll();
}
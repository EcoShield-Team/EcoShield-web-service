package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.*;

import java.util.List;

public interface IUsuarioServices {
    public UsuarioProfileDTO findById(Long id);
    public UsuarioProfileDTO updateProfile(Long id, UsuarioProfileDTO usuarioProfileDTO);
    public List<UsuarioResponseDTO> findAll();
}

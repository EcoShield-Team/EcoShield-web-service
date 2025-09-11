package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.*;

import java.util.List;

public interface IUsuarioServices {
    public UsuarioProfileDTO findById(Integer id);
    public UsuarioProfileDTO updateProfile(Integer id, UsuarioProfileDTO usuarioProfileDTO);
    public List<UsuarioResponseDTO> findAll();
}

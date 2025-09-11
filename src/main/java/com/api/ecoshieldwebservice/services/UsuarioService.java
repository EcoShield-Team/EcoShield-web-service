package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.*;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IUsuarioServices;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UsuarioProfileDTO findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return modelMapper.map(usuario, UsuarioProfileDTO.class);
    }

    @Override
    public UsuarioProfileDTO updateProfile(Integer id, UsuarioProfileDTO usuarioProfileDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        usuario.setUsuariofotoperfil(usuarioProfileDTO.getUsuariofotoperfil());
        usuario.setUsuariopais(usuarioProfileDTO.getUsuariopais());

        Usuario usuarioUpdated = usuarioRepository.save(usuario);
        return modelMapper.map(usuarioUpdated, UsuarioProfileDTO.class);
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .toList();
    }
}

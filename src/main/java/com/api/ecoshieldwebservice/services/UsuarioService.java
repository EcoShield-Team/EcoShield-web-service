package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.user.UsuarioProfileDTO;
import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.request.UsuarioUpdateDTO;
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
    public UsuarioProfileDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return modelMapper.map(usuario, UsuarioProfileDTO.class);
    }

    @Override
    public UsuarioProfileDTO updateProfile(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        usuario.setUsuarioFotoPerfil(dto.getUsuarioFotoPerfil());
        usuario.setUsuarioPais(dto.getUsuarioPais());

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
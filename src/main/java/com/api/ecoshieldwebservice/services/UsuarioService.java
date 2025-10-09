package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.user.UsuarioProfileDTO;
import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.request.UsuarioUpdateDTO;
import com.api.ecoshieldwebservice.entities.Rol;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.RolNombre;
import com.api.ecoshieldwebservice.enums.UsuarioEstado;
import com.api.ecoshieldwebservice.interfaces.IUsuarioService;
import com.api.ecoshieldwebservice.repositories.RolRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CloudinaryService cloudinaryService;


    @Override
    public UsuarioProfileDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioProfileDTO.class);
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuario -> {
                    UsuarioResponseDTO dto = modelMapper.map(usuario, UsuarioResponseDTO.class);
                    dto.setRolNombre(usuario.getRol().getRolNombre());
                    dto.setUsuarioEstado(usuario.getUsuarioEstado());
                    return dto;
                })
                .toList();
    }

    @Override
    public UsuarioProfileDTO updateProfile(Long id, UsuarioUpdateDTO dto, MultipartFile imagen) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        usuario.setUsuarioNombre(dto.getUsuarioNombre());
        usuario.setUsuarioPais(dto.getUsuarioPais());

        if (imagen != null && !imagen.isEmpty()) {
            String url = cloudinaryService.uploadImage(imagen);
            usuario.setUsuarioFotoPerfil(url);
        }

        Usuario actualizado = usuarioRepository.save(usuario);
        return modelMapper.map(actualizado, UsuarioProfileDTO.class);
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (usuario.getUsuarioEstado() == UsuarioEstado.BLOQUEADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ya está bloqueado");
        }

        usuario.setUsuarioEstado(UsuarioEstado.BLOQUEADO);
        usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioResponseDTO asignarRol(Long id, RolNombre nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Rol rol = rolRepository.findByRolNombre(nuevoRol)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        usuario.setRol(rol);
        Usuario actualizado = usuarioRepository.save(usuario);

        UsuarioResponseDTO dto = modelMapper.map(actualizado, UsuarioResponseDTO.class);
        dto.setRolNombre(rol.getRolNombre());
        dto.setUsuarioEstado(usuario.getUsuarioEstado());
        return dto;
    }

    public void marcarOnline(String correo) {
        Usuario u = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        if (u.getUsuarioEstado() != UsuarioEstado.BLOQUEADO) {
            u.setUsuarioEstado(UsuarioEstado.ACTIVO);
            usuarioRepository.save(u);
        }
    }

    public void marcarOffline(String correo) {
        Usuario u = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        if (u.getUsuarioEstado() != UsuarioEstado.BLOQUEADO) {
            u.setUsuarioEstado(UsuarioEstado.INACTIVO);
            usuarioRepository.save(u);
        }
    }
}
package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.*;
import com.api.ecoshieldwebservice.entities.Rol;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.UsuarioEstado;
import com.api.ecoshieldwebservice.interfaces.IAuthServices;
import com.api.ecoshieldwebservice.repositories.RolRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@Service
public class AuthService  implements IAuthServices {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public UsuarioRegisterDTO register(UsuarioRegisterDTO usuarioRegisterDTO) {

        if (usuarioRepository.existsByUsuariocorreo(usuarioRegisterDTO.getUsuariocorreo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Correo ya registrado");
        }

        Rol rolUser = rolRepository.findByRolnombre("USUARIO")
                .orElseThrow(() -> new RuntimeException("Rol USUARIO no encontrado"));

        Usuario usuario = modelMapper.map(usuarioRegisterDTO, Usuario.class);

        usuario.setUsuarioestado(UsuarioEstado.ACTIVO.name());
        usuario.setUsuariopais("PERU");
        usuario.setUsuariofecharegistro(OffsetDateTime.now());
        usuario.setRolid(rolUser);

        usuarioRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioRegisterDTO.class);
    }

    @Override
    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO) {
        Usuario usuario = usuarioRepository.findByUsuariocorreo(usuarioLoginDTO.getUsuariocorreo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos"));

        if (!usuario.getUsuariocontrasena().equals(usuarioLoginDTO.getUsuariocontrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos");
        }

        return modelMapper.map(usuario, UsuarioLoginDTO.class);
    }

    @Override
    public PasswordResetRequestDTO resetPassword(PasswordResetRequestDTO passwordResetRequestDTO) {
        Usuario usuario = usuarioRepository.findByUsuariocorreo(passwordResetRequestDTO.getUsuariocorreo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        System.out.println("Enviando link de reseteo a: " + usuario.getUsuariocorreo());

        return passwordResetRequestDTO;
    }

    @Override
    public PasswordChangeDTO changePassword(PasswordChangeDTO passwordChangeDTO) {
        Usuario usuario = usuarioRepository.findByUsuariocorreo(passwordChangeDTO.getUsuariocorreo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!usuario.getUsuariocontrasena().equals(passwordChangeDTO.getActualcontrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña actual incorrecta");
        }

        usuario.setUsuariocontrasena(passwordChangeDTO.getNuevacontrasena());
        usuarioRepository.save(usuario);

        return passwordChangeDTO;
    }
}

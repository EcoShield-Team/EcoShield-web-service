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

        if (usuarioRepository.existsByUsuarioCorreo(usuarioRegisterDTO.getUsuarioCorreo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Correo ya registrado");
        }

        Rol rolUser = rolRepository.findByRolNombre("USUARIO")
                .orElseThrow(() -> new RuntimeException("Rol USUARIO no encontrado"));

        Usuario usuario = modelMapper.map(usuarioRegisterDTO, Usuario.class);

        usuario.setUsuarioEstado(UsuarioEstado.ACTIVO.name());
        usuario.setUsuarioPais("PERU");
        usuario.setUsuarioFechaRegistro(OffsetDateTime.now());
        usuario.setRol(rolUser);

        usuarioRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioRegisterDTO.class);
    }

    @Override
    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO) {
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(usuarioLoginDTO.getUsuarioCorreo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos"));

        if (!usuario.getUsuarioContrasena().equals(usuarioLoginDTO.getUsuarioContrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo o contraseña incorrectos");
        }

        return modelMapper.map(usuario, UsuarioLoginDTO.class);
    }

    @Override
    public PasswordResetRequestDTO resetPassword(PasswordResetRequestDTO passwordResetRequestDTO) {
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(passwordResetRequestDTO.getUsuarioCorreo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        System.out.println("Enviando link de reseteo a: " + usuario.getUsuarioCorreo());

        return passwordResetRequestDTO;
    }

    @Override
    public PasswordChangeDTO changePassword(PasswordChangeDTO passwordChangeDTO) {
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(passwordChangeDTO.getUsuarioCorreo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!usuario.getUsuarioContrasena().equals(passwordChangeDTO.getActualContrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña actual incorrecta");
        }

        usuario.setUsuarioContrasena(passwordChangeDTO.getNuevaContrasena());
        usuarioRepository.save(usuario);

        return passwordChangeDTO;
    }
}

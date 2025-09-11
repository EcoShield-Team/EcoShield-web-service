package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.*;
import com.api.ecoshieldwebservice.entities.Rol;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.UsuarioEstado;
import com.api.ecoshieldwebservice.interfaces.IAuthServices;
import com.api.ecoshieldwebservice.interfaces.IUsuarioServices;
import com.api.ecoshieldwebservice.repositories.RolRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new RuntimeException("Correo ya registrado");
        }

        // Buscar el rol por defecto (USUARIO)
        Rol rolUser = rolRepository.findByRolnombre("USUARIO")
                .orElseThrow(() -> new RuntimeException("Rol USUARIO no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setUsuarionombre(usuarioRegisterDTO.getUsuarionombre());
        usuario.setUsuariocorreo(usuarioRegisterDTO.getUsuariocorreo());
        usuario.setUsuariocontrasena(usuarioRegisterDTO.getUsuariocontrasena());
        usuario.setUsuarioestado(UsuarioEstado.ACTIVO.name());
        usuario.setUsuariopais("PERU");
        usuario.setUsuariofecharegistro(OffsetDateTime.now());
        usuario.setRolid(rolUser);

        usuarioRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioRegisterDTO.class);
    }

    @Override
    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO) {
        return null;
    }

    @Override
    public PasswordResetRequestDTO resetPassword(PasswordResetRequestDTO passwordResetRequestDTO) {
        return null;
    }

    @Override
    public PasswordChangeDTO changePassword(PasswordChangeDTO passwordChangeDTO) {
        return null;
    }
}

package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.auth.*;
import com.api.ecoshieldwebservice.dtos.auth.password.ChangePasswordRequestDTO;
import com.api.ecoshieldwebservice.dtos.auth.password.ResetPasswordRequestDTO;
import com.api.ecoshieldwebservice.entities.Rol;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.RolNombre;
import com.api.ecoshieldwebservice.enums.UsuarioEstado;
import com.api.ecoshieldwebservice.interfaces.IAuthService;
import com.api.ecoshieldwebservice.repositories.RolRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import com.api.ecoshieldwebservice.security.jwt.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class AuthService  implements IAuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsuarioCorreo(), request.getUsuarioContrasena())
        );

        Usuario u = usuarioRepository.findByUsuarioCorreo(request.getUsuarioCorreo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        String springRole = "ROLE_" + u.getRol().getRolNombre().name();
        String token = jwtUtil.generateToken(u.getUsuarioCorreo(), springRole);
        Instant expiresAt = jwtUtil.extractExpiration(token).toInstant();

        UsuarioAuthResponseDTO usuarioDTO = modelMapper.map(u, UsuarioAuthResponseDTO.class);
        usuarioDTO.setUsuarioRol(springRole);

        return new AuthResponseDTO(token, expiresAt, usuarioDTO);
    }

    @Override
    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (usuarioRepository.existsByUsuarioCorreo(dto.getUsuarioCorreo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está registrado");
        }

        Rol rolUser = rolRepository.findByRolNombre(RolNombre.USER)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "No existe el rol USER en la base de datos"));

        if (dto.getUsuarioPais() == null || dto.getUsuarioPais().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El país es obligatorio");
        }

        Usuario nuevo = modelMapper.map(dto, Usuario.class);
        nuevo.setUsuarioContrasena(passwordEncoder.encode(dto.getUsuarioContrasena()));
        nuevo.setRol(rolUser);
        nuevo.setUsuarioEstado(UsuarioEstado.ACTIVO);

        Usuario saved = usuarioRepository.save(nuevo);

        String springRole = "ROLE_" + saved.getRol().getRolNombre().name();
        String token = jwtUtil.generateToken(saved.getUsuarioCorreo(), springRole);
        Instant expiresAt = jwtUtil.extractExpiration(token).toInstant();

        UsuarioAuthResponseDTO usuarioDTO = modelMapper.map(saved, UsuarioAuthResponseDTO.class);
        usuarioDTO.setUsuarioRol(springRole);

        return new AuthResponseDTO(token, expiresAt, usuarioDTO);
    }

    @Override
    public AuthResponseDTO changeMyPassword(String correo, ChangePasswordRequestDTO dto) {
        Usuario u = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getCurrentPassword(), u.getUsuarioContrasena())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña actual es incorrecta");
        }
        if (!dto.getNewPassword().equals(dto.getConfirmNewPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La confirmación no coincide");
        }
        if (passwordEncoder.matches(dto.getNewPassword(), u.getUsuarioContrasena())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La nueva contraseña no puede ser igual a la actual");
        }

        u.setUsuarioContrasena(passwordEncoder.encode(dto.getNewPassword()));
        usuarioRepository.save(u);

        String springRole = "ROLE_" + u.getRol().getRolNombre().name();
        String token = jwtUtil.generateToken(u.getUsuarioCorreo(), springRole);
        Instant expiresAt = jwtUtil.extractExpiration(token).toInstant();

        UsuarioAuthResponseDTO usuarioDTO = modelMapper.map(u, UsuarioAuthResponseDTO.class);
        usuarioDTO.setUsuarioRol(springRole);

        return new AuthResponseDTO(token, expiresAt, usuarioDTO);
    }
}
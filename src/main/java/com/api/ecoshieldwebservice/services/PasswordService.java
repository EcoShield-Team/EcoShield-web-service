package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.auth.password.*;
import com.api.ecoshieldwebservice.entities.Password;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IEmailService;
import com.api.ecoshieldwebservice.interfaces.IPasswordService;
import com.api.ecoshieldwebservice.repositories.PasswordRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import com.api.ecoshieldwebservice.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PasswordService implements IPasswordService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    IEmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${app.reset.base-url:}")
    private String resetBaseUrl;

    @Value("${app.reset.expose-token:false}")
    private boolean exposeToken;

    private String buildResetLink(String token) {
        if (resetBaseUrl == null || resetBaseUrl.isBlank()) {
            // fallback que apunta a tu backend, útil para Postman
            return "http://localhost:8080/auth/password/validate?token=" + token;
        }
        return resetBaseUrl + (resetBaseUrl.contains("?") ? "&" : "?") + "token=" + token;
    }


    @Override
    public ForgotPasswordResponseDTO requestReset(ForgotPasswordRequestDTO req) {
        String email = req.getEmail().trim().toLowerCase();
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El correo no está asociado a ninguna cuenta"));

        List<Password> actives = passwordRepository.findAllByUsuarioAndUsedAtIsNull(usuario);
        actives.forEach(t -> t.setUsedAt(OffsetDateTime.now()));
        if (!actives.isEmpty()) passwordRepository.saveAll(actives);

        // Crear nuevo token 24h
        OffsetDateTime now = OffsetDateTime.now();
        Password prt = PasswordUtil.createToken(usuario,24L);
        passwordRepository.save(prt);

        // Construir link real para tu frontend
        // Ajusta dominio/puerto según tu app web
        String resetLink = buildResetLink(prt.getToken());
        emailService.sendPasswordReset(email, resetLink);

        return exposeToken
                ? new ForgotPasswordResponseDTO("Se envió un enlace de recuperación al correo.", prt.getToken())
                : new ForgotPasswordResponseDTO("Se envió un enlace de recuperación al correo.", null);
    }

    @Override
    public ValidateTokenResponseDTO validateToken(String tokenValue) {
        Password token = passwordRepository.findByToken(tokenValue)
                .orElse(null);
        if (token == null) return new ValidateTokenResponseDTO(false, "Token inválido");
        if (PasswordUtil.isUsed(token)) return new ValidateTokenResponseDTO(false, "El token ya fue utilizado");
        if (PasswordUtil.isExpired(token)) return new ValidateTokenResponseDTO(false, "El token ha expirado");
        return new ValidateTokenResponseDTO(true, "Token válido");
    }

    @Override
    public ResetPasswordResponseDTO resetPassword(ResetPasswordRequestDTO req) {
        Password token = passwordRepository.findByToken(req.getToken())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token inválido"));

        if (PasswordUtil.isUsed(token)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El token ya fue utilizado");
        if (PasswordUtil.isExpired(token)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El token ha expirado");

        Usuario u = token.getUsuario();
        u.setUsuarioContrasena(passwordEncoder.encode(req.getNewPassword()));
        usuarioRepository.save(u);

        token.setUsedAt(OffsetDateTime.now());
        passwordRepository.save(token);

        return new ResetPasswordResponseDTO("La contraseña se actualizó correctamente.");
    }
}

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
import org.springframework.transaction.annotation.Transactional;
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

    @Value("${app.reset.expose-token}")
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

        OffsetDateTime now = OffsetDateTime.now();
        Password prt = PasswordUtil.createToken(usuario,15L);
        passwordRepository.save(prt);
        String resetLink = buildResetLink(prt.getToken());
        emailService.sendPasswordReset(email, resetLink, prt.getVerificationCode());

        return exposeToken
                ? new ForgotPasswordResponseDTO("Se envió un enlace de recuperación al correo.", prt.getToken(),prt.getVerificationCode())
                : new ForgotPasswordResponseDTO("Se envió un enlace de recuperación al correo.", null,null);
    }

    @Override
    public ValidateTokenResponseDTO verifyCode(VerifyCodeRequestDTO req) {
        String email = req.getEmail().trim().toLowerCase();

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El correo no está asociado a ninguna cuenta"));

        Password token = passwordRepository
                .findFirstByUsuarioAndVerificationCodeAndUsedAtIsNullOrderByCreatedAtDesc(usuario, req.getCode())
                .orElse(null);

        if (token == null) {
            return new ValidateTokenResponseDTO(false, "Código inválido o no encontrado", null);
        }

        if (PasswordUtil.isUsed(token)) {
            return new ValidateTokenResponseDTO(false, "El token ya fue utilizado", null);
        }

        if (PasswordUtil.isExpired(token)) {
            return new ValidateTokenResponseDTO(false, "El token ha expirado", null);
        }

        // OK: código válido, devolvemos el token para que el front pueda usar /auth/password/reset
        return new ValidateTokenResponseDTO(true, "Código válido", token.getToken());
    }

    @Override
    @Transactional
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

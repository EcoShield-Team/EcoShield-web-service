package com.api.ecoshieldwebservice.controllers;


import com.api.ecoshieldwebservice.dtos.auth.password.*;
import com.api.ecoshieldwebservice.interfaces.IPasswordService;
import com.api.ecoshieldwebservice.util.SimpleRateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/password")
public class PasswordResetController {
    @Autowired
    IPasswordService passwordService;

    @Autowired
    SimpleRateLimiter simpleRateLimiter;

    @PostMapping("/forgot")
    public ResponseEntity<ForgotPasswordResponseDTO> forgotPassword(HttpServletRequest request,
                                                                    @Valid @RequestBody ForgotPasswordRequestDTO body) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) ip = request.getRemoteAddr();

        if (!simpleRateLimiter.allow("forgot:" + ip)) {
            return ResponseEntity.status(429).body(
                    new ForgotPasswordResponseDTO("Demasiadas solicitudes. Inténtalo en un minuto.", null)
            );
        }
        return ResponseEntity.ok(passwordService.requestReset(body));
    }

    @GetMapping("/validate")
    public ResponseEntity<ValidateTokenResponseDTO> validateToken(@RequestParam("token") String token) {
        ValidateTokenResponseDTO response = passwordService.validateToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<ResetPasswordResponseDTO> resetPassword(@Valid @RequestBody ResetPasswordRequestDTO request) {
        ResetPasswordResponseDTO response = passwordService.resetPassword(request);
        return ResponseEntity.ok(response);
    }
}

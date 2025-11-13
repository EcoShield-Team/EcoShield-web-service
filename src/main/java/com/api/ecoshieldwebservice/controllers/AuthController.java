package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.auth.*;
import com.api.ecoshieldwebservice.dtos.auth.password.*;
import com.api.ecoshieldwebservice.interfaces.IAuthService;
import com.api.ecoshieldwebservice.interfaces.IPasswordService;
import com.api.ecoshieldwebservice.util.SimpleRateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Endpoints públicos para autenticación y registro de usuarios")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    IPasswordService passwordService;

    @Autowired
    SimpleRateLimiter simpleRateLimiter;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        AuthResponseDTO res = authService.register(dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + res.getToken())
                .body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        AuthResponseDTO res = authService.login(dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + res.getToken())
                .body(res);
    }

    @PutMapping("/password/change")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AuthResponseDTO> changePassword(@AuthenticationPrincipal UserDetails user, @Valid @RequestBody ChangePasswordRequestDTO dto) {
        AuthResponseDTO res = authService.changeMyPassword(user.getUsername(), dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + res.getToken())
                .body(res);
    }

    @PostMapping("/password/forgot")
    public ResponseEntity<ForgotPasswordResponseDTO> forgotPassword(HttpServletRequest request, @Valid @RequestBody ForgotPasswordRequestDTO body) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) ip = request.getRemoteAddr();

        if (!simpleRateLimiter.allow("forgot:" + ip)) {
            return ResponseEntity.status(429).body(
                    new ForgotPasswordResponseDTO("Demasiadas solicitudes. Inténtalo en un minuto.", null,null)
            );
        }
        return ResponseEntity.ok(passwordService.requestReset(body));
    }

    @GetMapping("/password/validate")
    public ResponseEntity<ValidateTokenResponseDTO> validateToken(@RequestParam("token") String token) {
        ValidateTokenResponseDTO response = passwordService.validateToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<ResetPasswordResponseDTO> resetPassword(@Valid @RequestBody ResetPasswordRequestDTO request) {
        ResetPasswordResponseDTO response = passwordService.resetPassword(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/password/verify-code")
    public ResponseEntity<ValidateTokenResponseDTO> verifyCode(@Valid @RequestBody VerifyCodeRequestDTO request) {
        ValidateTokenResponseDTO response = passwordService.verifyCode(request);
        return ResponseEntity.ok(response);
    }
}
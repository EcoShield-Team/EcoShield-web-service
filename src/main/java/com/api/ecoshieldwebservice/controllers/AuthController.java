package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.auth.*;
import com.api.ecoshieldwebservice.interfaces.IAuthServices;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthServices authService;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        AuthResponseDTO res = authService.register(dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + res.getToken())
                .body(res);
    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        AuthResponseDTO res = authService.login(dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + res.getToken())
                .body(res);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioAuthResponseDTO> me(@AuthenticationPrincipal UserDetails user) {
        UsuarioAuthResponseDTO dto = new UsuarioAuthResponseDTO();
        dto.setUsuarioCorreo(user.getUsername());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AuthResponseDTO> changePassword(@AuthenticationPrincipal UserDetails user, @Valid @RequestBody ChangePasswordRequestDTO dto) {
        AuthResponseDTO res = authService.changeMyPassword(user.getUsername(), dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + res.getToken())
                .body(res);
    }
}
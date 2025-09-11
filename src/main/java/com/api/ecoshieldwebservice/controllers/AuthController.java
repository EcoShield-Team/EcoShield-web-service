package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.PasswordChangeDTO;
import com.api.ecoshieldwebservice.dtos.PasswordResetRequestDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioLoginDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioRegisterDTO;
import com.api.ecoshieldwebservice.services.AuthService;
import com.api.ecoshieldwebservice.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public UsuarioRegisterDTO register(@RequestBody UsuarioRegisterDTO usuarioRegisterDTO) {
        return authService.register(usuarioRegisterDTO);
    }

    @PostMapping("/auth/login")
    public UsuarioLoginDTO login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        return authService.login(usuarioLoginDTO);
    }

    @PostMapping("/auth/reset-password")
    public PasswordResetRequestDTO resetPassword(@RequestBody PasswordResetRequestDTO passwordResetRequestDTO) {
        return authService.resetPassword(passwordResetRequestDTO);
    }

    @PostMapping("/auth/change-password")
    public PasswordChangeDTO changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        return authService.changePassword(passwordChangeDTO);
    }
}

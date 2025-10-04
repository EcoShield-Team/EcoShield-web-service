package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.PasswordChangeDTO;
import com.api.ecoshieldwebservice.dtos.PasswordResetRequestDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioLoginDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioRegisterDTO;
import com.api.ecoshieldwebservice.interfaces.IAuthServices;
import com.api.ecoshieldwebservice.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthServices authService;


    @PostMapping("/register")
    public ResponseEntity<UsuarioRegisterDTO> register(@Valid @RequestBody UsuarioRegisterDTO usuarioRegisterDTO) {
        UsuarioRegisterDTO createdUser = authService.register(usuarioRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginDTO> login(@Valid @RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        UsuarioLoginDTO loggedUser = authService.login(usuarioLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loggedUser);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<PasswordResetRequestDTO> resetPassword(@Valid @RequestBody PasswordResetRequestDTO passwordResetRequestDTO) {
        PasswordResetRequestDTO response = authService.resetPassword(passwordResetRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<PasswordChangeDTO> changePassword(@Valid @RequestBody PasswordChangeDTO passwordChangeDTO) {
        PasswordChangeDTO response = authService.changePassword(passwordChangeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

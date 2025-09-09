package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.*;
import com.api.ecoshieldwebservice.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/auth/register")
    public UsuarioRegisterDTO register(@RequestBody UsuarioRegisterDTO usuarioRegisterDTO) {
        return usuarioService.register(usuarioRegisterDTO);
    }

    @PostMapping("/auth/login")
    public UsuarioLoginDTO login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        return usuarioService.login(usuarioLoginDTO);
    }

    @PostMapping("/auth/reset-password")
    public PasswordResetRequestDTO resetPassword(@RequestBody PasswordResetRequestDTO passwordResetRequestDTO) {
        return usuarioService.resetPassword(passwordResetRequestDTO);
    }

    @PostMapping("/auth/change-password")
    public PasswordChangeDTO  changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        return usuarioService.changePassword(passwordChangeDTO);
    }



    @GetMapping("/users")
    public List<UsuarioResponseDTO> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/users/{id}")
    public UsuarioProfileDTO findById(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/users/{id}")
    public UsuarioProfileDTO updateProfile(@PathVariable Integer id, @RequestBody UsuarioProfileDTO usuarioProfileDTO) {
        return usuarioService.updateProfile(id, usuarioProfileDTO);
    }


}

package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.*;
import com.api.ecoshieldwebservice.dtos.response.UsuarioResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IUsuarioServices;
import com.api.ecoshieldwebservice.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioServices usuarioService;


    @GetMapping("/users")
    public List<UsuarioResponseDTO> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/users/{id}")
    public UsuarioProfileDTO findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/users/{id}")
    public UsuarioProfileDTO updateProfile(@PathVariable Long id, @Valid @RequestBody UsuarioProfileDTO usuarioProfileDTO) {
        return usuarioService.updateProfile(id, usuarioProfileDTO);
    }

}

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

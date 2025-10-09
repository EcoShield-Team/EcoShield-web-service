package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.response.DeteccionResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IDeteccionService;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "Deteccion", description = "Deteccion de enfermedades y plagas en plantas")
@RestController
@RequestMapping("/deteccion")
public class DeteccionController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IDeteccionService deteccionService;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<DeteccionResponseDTO> analizar(
            @RequestPart("imagen") MultipartFile imagen,
            Authentication authentication) {

        String correo = authentication.getName();
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        DeteccionResponseDTO result = deteccionService.analizarCultivo(imagen, usuario);
        return ResponseEntity.ok(result);
    }
}
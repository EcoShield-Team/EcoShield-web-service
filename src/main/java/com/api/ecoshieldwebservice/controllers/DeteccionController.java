package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.response.DeteccionResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IDeteccionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Deteccion", description = "Análisis y gestión de detecciones de plagas y enfermedades en cultivos")
@RestController
@RequestMapping("/deteccion")
public class DeteccionController {

    @Autowired
    private IDeteccionService deteccionService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<DeteccionResponseDTO> analizar(
            @RequestPart("imagen") MultipartFile imagen,
            Authentication authentication) {

        DeteccionResponseDTO result = deteccionService.analizarCultivo(imagen, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<DeteccionResponseDTO> obtenerResultado(
            @PathVariable Long id,
            Authentication authentication) {

        DeteccionResponseDTO dto = deteccionService.obtenerResultado(id, authentication.getName());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/historial")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<DeteccionResponseDTO>> obtenerHistorial(Authentication authentication) {
        List<DeteccionResponseDTO> historial = deteccionService.listarHistorial(authentication.getName());
        return ResponseEntity.ok(historial);
    }
}

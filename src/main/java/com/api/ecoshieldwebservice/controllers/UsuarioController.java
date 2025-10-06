package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.user.UsuarioProfileDTO;
import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.request.UsuarioUpdateDTO;
import com.api.ecoshieldwebservice.enums.RolNombre;
import com.api.ecoshieldwebservice.interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioProfileDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UsuarioProfileDTO> updateProfile(
            @PathVariable Long id,
            @Valid @RequestPart("data") UsuarioUpdateDTO dto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {

        return ResponseEntity.ok(usuarioService.updateProfile(id, dto, imagen));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rol")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> asignarRol(
            @PathVariable Long id,
            @RequestParam("rol") RolNombre nuevoRol) {

        return ResponseEntity.ok(usuarioService.asignarRol(id, nuevoRol));
    }
}
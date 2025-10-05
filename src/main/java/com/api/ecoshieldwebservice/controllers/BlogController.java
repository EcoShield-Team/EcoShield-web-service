package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IBlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlogResponseDTO> registrar(
            @Valid @RequestPart("data") BlogRequestDTO dto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            Authentication authentication) {

        BlogResponseDTO nuevoBlog = blogService.registrar(dto, imagen, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBlog);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlogResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestPart("data") BlogRequestDTO dto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            Authentication authentication) {

        BlogResponseDTO actualizado = blogService.actualizar(id, dto, imagen, authentication.getName());
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        blogService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlogResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<BlogResponseDTO>> findAll() {
        return ResponseEntity.ok(blogService.findAll());
    }

    @GetMapping("/tip")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<BlogResponseDTO> findTipDelDia() {
        return ResponseEntity.ok(blogService.findTipDelDia());
    }

    @GetMapping("/news")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<BlogResponseDTO>> findAllNews() {
        return ResponseEntity.ok(blogService.findAllNews());
    }
}


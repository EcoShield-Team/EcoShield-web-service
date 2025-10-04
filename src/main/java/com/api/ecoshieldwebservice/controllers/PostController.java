package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private IPostService postService;


    @PostMapping("/posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> crearPost(@Valid @RequestBody PostRequestDTO dto) {
        PostResponseDTO created = postService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/posts/{id}")
    @PreAuthorize("hasPermission(#id, 'POST', 'UPDATE')")
    public ResponseEntity<PostResponseDTO> actualizarPost(@PathVariable Long id,
                                                          @Valid @RequestBody PostRequestDTO dto) {
        return ResponseEntity.ok(postService.actualizar(id, dto));
    }

    @GetMapping("/posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PostResponseDTO>> findAllPosts(@RequestParam(required = false) String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            return ResponseEntity.ok(postService.findByPosttitulo(titulo));
        }
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#id, 'POST', 'DELETE')")
    public ResponseEntity<Void> borrarPost(@PathVariable Long id) {
        postService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/{usuarioId}/posts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PostResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(postService.findByUsuarioid(usuarioId));
    }
}

package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDTO> crearPost(@Valid @RequestBody PostRequestDTO dto) {
        PostResponseDTO created = postService.registrar(dto);
        if (created != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> actualizarPost(@PathVariable Long id, @Valid @RequestBody PostRequestDTO dto) {
        PostResponseDTO updated = postService.actualizar(id, dto);
        if (updated != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> findAllPosts(@Valid @RequestParam(required = false) String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            List<PostResponseDTO> lista = postService.findByPosttitulo(titulo);
            if (lista == null || lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        }
        List<PostResponseDTO> lista = postService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id) {
        PostResponseDTO post = postService.findById(id);
        if (post == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> borrarPost(@PathVariable Long id) {
        postService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/{usuarioId}/posts")
    public ResponseEntity<List<PostResponseDTO>> listarPorUsuario(@PathVariable Usuario usuarioId) {
        List<PostResponseDTO> post = postService.findByUsuarioid(usuarioId);
        if (post == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(post);
    }

}

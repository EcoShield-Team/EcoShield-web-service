package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.services.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/posts/{postId}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> crear(@PathVariable Long postId, @Valid @RequestBody ComentarioRequestDTO dto) {
        dto.setPostId(postId);
        ComentarioResponseDTO created = comentarioService.registrar(dto);
        if (created != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/posts/{postId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioResponseDTO> actualizar(@PathVariable Long postId, @PathVariable Long comentarioId, @Valid @RequestBody ComentarioRequestDTO dto) {
        dto.setPostId(postId);
        ComentarioResponseDTO updated = comentarioService.actualizar(postId, comentarioId, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/posts/{postId}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorPost(@PathVariable Long postId) {
        List<ComentarioResponseDTO> lista = comentarioService.findByPostId(postId);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuarios/{usuarioId}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> findByUsuarioid(@PathVariable Long usuarioId) {
        List<ComentarioResponseDTO> lista = comentarioService.findByUsuarioid(usuarioId);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/comentarios/{comentarioid}")
    public ResponseEntity<ComentarioResponseDTO> findComentarioById(@PathVariable Long comentarioid) {
        ComentarioResponseDTO comentario = comentarioService.findById(comentarioid);
        if (comentario == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/posts/{postId}/comentarios/{comentarioId}")
    public ResponseEntity<Void> borrar(@PathVariable Long postId, @PathVariable Long comentarioId) {
        comentarioService.borrar(postId, comentarioId);
        return ResponseEntity.noContent().build();
    }
}

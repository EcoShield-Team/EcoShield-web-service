package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IComentarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comentario", description = "Gestión de comentarios sobre posts")
@RestController
public class ComentarioController {

    @Autowired
    private IComentarioService comentarioService;


    @PostMapping("/posts/{postId}/comentarios")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ComentarioResponseDTO> crear(@PathVariable Long postId,
                                                       @Valid @RequestBody ComentarioRequestDTO dto,
                                                       Authentication authentication) {
        dto.setPostId(postId);
        ComentarioResponseDTO created = comentarioService.registrar(dto, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/posts/{postId}/comentarios/{comentarioId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ComentarioResponseDTO> actualizar(@PathVariable Long postId,
                                                            @PathVariable Long comentarioId,
                                                            @Valid @RequestBody ComentarioRequestDTO dto,
                                                            Authentication authentication) {
        dto.setPostId(postId);
        ComentarioResponseDTO updated = comentarioService.actualizar(postId, comentarioId, dto, authentication.getName());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/posts/{postId}/comentarios/{comentarioId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> borrar(@PathVariable Long postId,
                                       @PathVariable Long comentarioId,
                                       Authentication authentication) {
        comentarioService.borrar(postId, comentarioId, authentication.getName(), authentication.getAuthorities());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/{postId}/comentarios")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorPost(@PathVariable Long postId) {
        return ResponseEntity.ok(comentarioService.findByPostId(postId));
    }

    @GetMapping("/usuarios/{usuarioId}/comentarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comentarioService.findByUsuarioid(usuarioId));
    }

    @GetMapping("/comentarios/{comentarioId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ComentarioResponseDTO> findComentarioById(@PathVariable Long comentarioId) {
        return ResponseEntity.ok(comentarioService.findById(comentarioId));
    }
}

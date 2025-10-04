package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComentarioController {

    @Autowired
    private IComentarioService comentarioService;


    @PostMapping("/posts/{postId}/comentarios")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ComentarioResponseDTO> crear(@PathVariable Long postId,
                                                       @Valid @RequestBody ComentarioRequestDTO dto) {
        dto.setPostId(postId);
        ComentarioResponseDTO created = comentarioService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/posts/{postId}/comentarios/{comentarioId}")
    @PreAuthorize("hasPermission(#comentarioId, 'POST', 'UPDATE')")
    public ResponseEntity<ComentarioResponseDTO> actualizar(@PathVariable Long postId,
                                                            @PathVariable Long comentarioId,
                                                            @Valid @RequestBody ComentarioRequestDTO dto) {
        dto.setPostId(postId);
        return ResponseEntity.ok(comentarioService.actualizar(postId, comentarioId, dto));
    }

    @GetMapping("/posts/{postId}/comentarios")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorPost(@PathVariable Long postId) {
        return ResponseEntity.ok(comentarioService.findByPostId(postId));
    }

    @DeleteMapping("/posts/{postId}/comentarios/{comentarioId}")
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#comentarioId, 'COMENTARIO', 'DELETE')")
    public ResponseEntity<Void> borrar(@PathVariable Long postId, @PathVariable Long comentarioId) {
        comentarioService.borrar(postId, comentarioId);
        return ResponseEntity.noContent().build();
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

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

        ComentarioResponseDTO created = comentarioService.registrar(postId, dto, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/posts/{postId}/comentarios/{comentarioId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ComentarioResponseDTO> actualizar(@PathVariable Long postId,
                                                            @PathVariable Long comentarioId,
                                                            @Valid @RequestBody ComentarioRequestDTO dto,
                                                            Authentication authentication) {

        return ResponseEntity.ok(comentarioService.actualizar(postId, comentarioId, dto, authentication.getName()));
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
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorPost(@PathVariable Long postId,
                                                                     Authentication auth) {
        return ResponseEntity.ok(comentarioService.findByPostId(postId, auth.getName()));
    }


    @GetMapping("/usuarios/{usuarioId}/comentarios")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ComentarioResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId,
                                                                        Authentication auth) {
        return ResponseEntity.ok(comentarioService.findByUsuarioid(usuarioId, auth.getName()));
    }

    @GetMapping("/comentarios/{comentarioId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ComentarioResponseDTO> findComentarioById(@PathVariable Long comentarioId) {
        return ResponseEntity.ok(comentarioService.findById(comentarioId));
    }
}

package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private IPostService postService;


    @PostMapping("/posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> crearPost(@Valid @RequestBody PostRequestDTO dto,
                                                     Authentication authentication) {
        PostResponseDTO created = postService.registrar(dto, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/posts/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> actualizarPost(@PathVariable Long id,
                                                          @Valid @RequestBody PostRequestDTO dto,
                                                          Authentication authentication) {
        PostResponseDTO updated = postService.actualizar(id, dto, authentication.getName());
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PostResponseDTO>> findAllPosts(@RequestParam(required = false) String titulo) {
        List<PostResponseDTO> posts = (titulo != null && !titulo.isBlank())
                ? postService.findByPosttitulo(titulo)
                : postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> borrarPost(@PathVariable Long id, Authentication authentication) {
        postService.borrar(id, authentication.getName(), authentication.getAuthorities());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/{usuarioId}/posts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PostResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(postService.findByUsuarioid(usuarioId));
    }

    @GetMapping("/posts/mis-posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PostResponseDTO>> listarMisPosts(Authentication authentication) {
        String correo = authentication.getName();
        List<PostResponseDTO> posts = postService.findByCorreo(correo);
        return ResponseEntity.ok(posts);
    }
}

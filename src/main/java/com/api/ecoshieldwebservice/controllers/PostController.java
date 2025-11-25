package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.SearchResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IPostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Post", description = "Gestión de publicaciones creadas por usuarios")
@RestController
public class PostController {

    @Autowired
    private IPostService postService;


    @PostMapping(value = "/posts",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> crearPost(
            @Valid @RequestPart("data") PostRequestDTO dto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            Authentication authentication) {

        PostResponseDTO created = postService.registrar(dto, imagen, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/posts/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> actualizarPost(
            @PathVariable Long id,
            @Valid @RequestPart("data") PostRequestDTO dto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            Authentication authentication) {

        PostResponseDTO updated = postService.actualizar(id, dto, imagen, authentication.getName());
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PostResponseDTO>> findAllPosts(Authentication auth) {
        String correo = auth != null ? auth.getName() : null;
        return ResponseEntity.ok(postService.findAll(correo));
    }

    @GetMapping("/posts/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(postService.findById(id, auth.getName()));
    }

    @GetMapping("/posts/search")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SearchResponseDTO> buscar(@RequestParam String query, @RequestParam String tipo,
                                                    Authentication auth) {

        String correoActual = auth != null ? auth.getName() : null;
        return ResponseEntity.ok(postService.buscar(query, tipo, correoActual));
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> borrarPost(@PathVariable Long id, Authentication authentication) {
        postService.borrar(id, authentication.getName(), authentication.getAuthorities());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/{usuarioId}/posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PostResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId, Authentication auth) {
        String correoActual = auth != null ? auth.getName() : null;
        return ResponseEntity.ok(postService.findByUsuarioid(usuarioId, correoActual));
    }

    @GetMapping("/posts/mis-posts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PostResponseDTO>> listarMisPosts(Authentication authentication) {
        String correo = authentication.getName();
        List<PostResponseDTO> posts = postService.findByCorreo(correo);
        return ResponseEntity.ok(posts);
    }
}

package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IPostService;
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
public class PostController {

    @Autowired
    private IPostService postService;


    @PostMapping(value = "/posts",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> crearPost(
            @Valid @RequestPart("data") String dataJson,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            Authentication authentication) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            PostRequestDTO dto = mapper.readValue(dataJson, PostRequestDTO.class);

            PostResponseDTO created = postService.registrar(dto, imagen, authentication.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato inválido de JSON en 'data'");
        }
    }

    @PutMapping(value = "/posts/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PostResponseDTO> actualizarPost(
            @PathVariable Long id,
            @Valid @RequestPart("data") String dataJson,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            Authentication authentication) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            PostRequestDTO dto = mapper.readValue(dataJson, PostRequestDTO.class);

            PostResponseDTO updated = postService.actualizar(id, dto, imagen, authentication.getName());
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato inválido de JSON en 'data'");
        }
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

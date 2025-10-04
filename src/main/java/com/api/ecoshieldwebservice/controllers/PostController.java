package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IPostServices;
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
    private IPostServices postService;


    @PostMapping("/posts")
    public ResponseEntity<PostResponseDTO> crearPost(@Valid @RequestBody PostRequestDTO dto) {
        PostResponseDTO created = postService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> actualizarPost(@PathVariable Long id,
                                                          @Valid @RequestBody PostRequestDTO dto) {
        return ResponseEntity.ok(postService.actualizar(id, dto));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> findAllPosts(@RequestParam(required = false) String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            return ResponseEntity.ok(postService.findByPosttitulo(titulo));
        }
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> borrarPost(@PathVariable Long id) {
        postService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/{usuarioId}/posts")
    public ResponseEntity<List<PostResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(postService.findByUsuarioid(usuarioId));
    }
}

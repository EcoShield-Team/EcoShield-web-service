package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public PostResponseDTO crearPost(@RequestBody PostRequestDTO dto) {
        return postService.registrar(dto);
    }

    @PutMapping("/posts/{id}")
    public PostResponseDTO actualizarPost(@PathVariable Integer id, @RequestBody PostRequestDTO dto) {
        return postService.actualizar(id, dto);
    }

    @GetMapping("/posts")
    public List<PostResponseDTO> findAllPosts(@RequestParam(required = false) String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            return postService.findByPosttitulo(titulo);
        }
        return postService.findAll();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDTO findById(@PathVariable Integer id) {
        return postService.findById(id);
    }

    @DeleteMapping("/posts/{id}")
    public void borrarPost(@PathVariable Integer id) {
        postService.borrar(id);
    }

    @GetMapping("/usuarios/{usuarioId}/posts")
    public List<PostResponseDTO> listarPorUsuario(@PathVariable Usuario usuarioId) {
        return postService.findByUsuarioid(usuarioId);
    }

}

package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/posts/{postId}/comentarios")
    public ComentarioResponseDTO crear(@PathVariable Long postId, @RequestBody ComentarioRequestDTO dto) {
        dto.setPostId(postId);
        return comentarioService.registrar(dto);
    }

    @PutMapping("/posts/{postId}/comentarios/{comentarioId}")
    public ComentarioResponseDTO actualizar(@PathVariable Long postId, @PathVariable Long comentarioId, @RequestBody ComentarioRequestDTO dto) {
        dto.setPostId(postId);
        return comentarioService.actualizar(postId, comentarioId, dto);
    }

    @GetMapping("/posts/{postId}/comentarios")
    public List<ComentarioResponseDTO> listarPorPost(@PathVariable Long postId) {
        return comentarioService.findByPostId(postId);
    }

    @GetMapping("/usuarios/{usuarioId}/comentarios")
    public List<ComentarioResponseDTO> findByUsuarioid(@PathVariable Long usuarioId) {
        return comentarioService.findByUsuarioid(usuarioId);
    }

    @GetMapping("/comentarios/{comentarioid}")
    public ComentarioResponseDTO findComentarioById(@PathVariable Long comentarioid) {
        return comentarioService.findById(comentarioid);
    }

    @DeleteMapping("/posts/{postId}/comentarios/{comentarioId}")
    public void borrar(@PathVariable Long postId, @PathVariable Long comentarioId) {
        comentarioService.borrar(postId, comentarioId);
    }
}

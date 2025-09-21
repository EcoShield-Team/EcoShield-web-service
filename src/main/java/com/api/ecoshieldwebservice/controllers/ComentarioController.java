package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.ComentarioRequestDTO;
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
    public ComentarioResponseDTO crear(@PathVariable Integer postId, @RequestBody ComentarioRequestDTO dto) {
        dto.setPostid(postId);
        return comentarioService.registrar(dto);
    }

    @PutMapping("/posts/{postId}/comentarios/{comentarioId}")
    public ComentarioResponseDTO actualizar(@PathVariable Integer postId, @PathVariable Integer comentarioId, @RequestBody ComentarioRequestDTO dto) {
        dto.setPostid(postId);
        return comentarioService.actualizar(postId, comentarioId, dto);
    }

    @GetMapping("/posts/{postId}/comentarios")
    public List<ComentarioResponseDTO> listarPorPost(@PathVariable Integer postId) {
        return comentarioService.findByPostId(postId);
    }

    @GetMapping("/usuarios/{usuarioId}/comentarios")
    public List<ComentarioResponseDTO> findByUsuarioid(@PathVariable Integer usuarioId) {
        return comentarioService.findByUsuarioid(usuarioId);
    }

    @GetMapping("/comentarios/{comentarioid}")
    public ComentarioResponseDTO findComentarioById(@PathVariable Integer comentarioid) {
        return comentarioService.findById(comentarioid);
    }

    @DeleteMapping("/posts/{postId}/comentarios/{comentarioId}")
    public void borrar(@PathVariable Integer postId, @PathVariable Integer comentarioId) {
        comentarioService.borrar(postId, comentarioId);
    }
}

package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/comentario")
    public ComentarioResponseDTO crearComentario(@RequestBody ComentarioRequestDTO comentarioRequestDTO) {
        return comentarioService.registrarComentario(comentarioRequestDTO);
    }

    @PutMapping("/comentario")
    public ComentarioResponseDTO actualizarComentario(@RequestBody ComentarioRequestDTO comentarioRequestDTO) {
        return comentarioService.actualizarComentario(comentarioRequestDTO);
    }

    @GetMapping("/comentario")
    public List<ComentarioResponseDTO> findAllComentarios() {
        return comentarioService.findAll();
    }

    @GetMapping("/comentario/{id}")
    public ComentarioResponseDTO findComentarioById(@PathVariable Integer id) {
        return comentarioService.findById(id);
    }

    @DeleteMapping("/comentario/{id}")
    public void borrarComentario(@PathVariable Integer id) {
        comentarioService.borrarComentario(id);
    }
}

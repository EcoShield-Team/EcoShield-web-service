package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.services.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> registrar(@Valid @RequestBody FeedbackRequestDTO dto) {
        FeedbackResponseDTO created = feedbackService.registrar(dto);
        if (created != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> findAll() {
        List<FeedbackResponseDTO> lista =  feedbackService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> findById(@PathVariable Long id) {
        FeedbackResponseDTO founded = feedbackService.findById(id);
        return ResponseEntity.ok(founded);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        feedbackService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FeedbackResponseDTO>> findByUsuarioid(@PathVariable Usuario usuarioId) {
        List<FeedbackResponseDTO> lista = feedbackService.findByUsuarioid(usuarioId);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<FeedbackResponseDTO>> findByTipo(@PathVariable String tipo) {
        List<FeedbackResponseDTO> lista = feedbackService.findByFeedbacktipo(tipo);
        if  (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }
}

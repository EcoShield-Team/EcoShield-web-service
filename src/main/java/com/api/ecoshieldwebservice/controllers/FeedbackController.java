package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> registrar(@RequestBody FeedbackRequestDTO dto) {
        var created = feedbackService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<FeedbackResponseDTO> findAll() {
        return feedbackService.findAll();
    }

    @GetMapping("/{id}")
    public FeedbackResponseDTO findById(@PathVariable Integer id) {
        return feedbackService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Integer id) {
        feedbackService.borrar(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<FeedbackResponseDTO> findByUsuario(@PathVariable Integer usuarioId) {
        return feedbackService.findByUsuarioid_Id(usuarioId);
    }

    @GetMapping("/tipo/{tipo}")
    public List<FeedbackResponseDTO> findByTipo(@PathVariable String tipo) {
        return feedbackService.findByFeedbacktipo(tipo);
    }
}

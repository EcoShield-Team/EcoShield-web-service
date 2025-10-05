package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.enums.FeedbackTipo;
import com.api.ecoshieldwebservice.interfaces.IFeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FeedbackResponseDTO> registrar(@Valid @RequestBody FeedbackRequestDTO dto,
                                                         Authentication authentication) {
        FeedbackResponseDTO created = feedbackService.registrar(dto, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FeedbackResponseDTO>> findAll() {
        return ResponseEntity.ok(feedbackService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeedbackResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.findById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        feedbackService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FeedbackResponseDTO>> findByUsuarioid(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(feedbackService.findByUsuarioid(usuarioId));
    }

    @GetMapping("/tipo/{tipo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FeedbackResponseDTO>> findByTipo(@PathVariable FeedbackTipo tipo) {
        return ResponseEntity.ok(feedbackService.findByFeedbacktipo(tipo));
    }
}

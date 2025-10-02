package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.entities.Feedback;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IFeedbackServices;
import com.api.ecoshieldwebservice.repositories.FeedbackRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService implements IFeedbackServices {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    private FeedbackResponseDTO EtoRespDTO(Feedback e) {
        FeedbackResponseDTO dto = modelMapper.map(e, FeedbackResponseDTO.class);
        dto.setUsuarioId(e.getUsuario().getUsuarioId());
        dto.setFeedbackId(e.getFeedbackId());
        dto.setFeedbackTipo(e.getFeedbackTipo());
        dto.setFeedbackDescripcion(e.getFeedbackDescripcion());
        dto.setFeedbackRating(e.getFeedbackRating());
        dto.setFeedbackFecha(e.getFeedbackFecha());
        return dto;
    }


    @Override
    public FeedbackResponseDTO findById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback no encontrado"));
        return EtoRespDTO(feedback);
    }

    @Override
    public FeedbackResponseDTO registrar(FeedbackRequestDTO dto) {
        Feedback feedback = modelMapper.map(dto, Feedback.class);
        Usuario u = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        feedback.setUsuario(u);
        feedback.setFeedbackFecha(OffsetDateTime.now());
        feedback = feedbackRepository.save(feedback);
        return EtoRespDTO(feedback);
    }


    @Override
    public void borrar(Long id) {
        if (!feedbackRepository.existsById(id))
            throw new EntityNotFoundException("Feedback no encontrado");
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<FeedbackResponseDTO> findAll() {
        return feedbackRepository.findAll().stream()
                .map(this::EtoRespDTO).toList();
    }

    @Override
    public List<FeedbackResponseDTO> findByUsuarioid(Usuario usuarioId) {
        return feedbackRepository.findByUsuario(usuarioId).stream()
                .map(this::EtoRespDTO).toList();
    }

    @Override
    public List<FeedbackResponseDTO> findByFeedbacktipo(String tipo) {
        return feedbackRepository.findByFeedbackTipo(tipo).stream()
                .map(this::EtoRespDTO).toList();
    }
}

package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.FeedbackRequestDTO;
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
    // ---------- Mapeos ----------
    private FeedbackResponseDTO EtoRespDTO(Feedback e) {
        FeedbackResponseDTO dto = modelMapper.map(e, FeedbackResponseDTO.class);
        dto.setUsuarioid(e.getUsuarioid().getUsuarioid());
        dto.setId(e.getFeedbackid());
        dto.setFeedbacktipo(e.getFeedbacktipo());
        dto.setFeedbackdescripcion(e.getFeedbackdescripcion());
        dto.setFeedbackrating(e.getFeedbackrating());
        dto.setFeedbackfecha(e.getFeedbackfecha());
        return dto;
    }

    // ---------- Métodos ----------
    @Override
    public FeedbackResponseDTO findById(Integer id) {
        Feedback e = feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback no encontrado"));
        return EtoRespDTO(e);
    }

    @Override
    public FeedbackResponseDTO registrar(FeedbackRequestDTO dto) {
        Feedback feedback = modelMapper.map(dto, Feedback.class);

        Usuario u = usuarioRepository.findById(dto.getUsuarioid())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        feedback.setUsuarioid(u);

        feedback = feedbackRepository.save(feedback);

        return modelMapper.map(feedback, FeedbackResponseDTO.class);
    }


    @Override
    public void borrar(Integer id) {
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
        return feedbackRepository.findByUsuarioid(usuarioId).stream()
                .map(this::EtoRespDTO).toList();
    }

    @Override
    public List<FeedbackResponseDTO> findByFeedbacktipo(String tipo) {
        return feedbackRepository.findByFeedbacktipo(tipo).stream()
                .map(this::EtoRespDTO).toList();
    }
}

package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.entities.Feedback;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.FeedbackTipo;
import com.api.ecoshieldwebservice.interfaces.IFeedbackService;
import com.api.ecoshieldwebservice.repositories.FeedbackRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public FeedbackResponseDTO registrar(FeedbackRequestDTO dto, String correo) {
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Feedback feedback = new Feedback();
        feedback.setFeedbackTipo(dto.getFeedbackTipo());
        feedback.setFeedbackDescripcion(dto.getFeedbackDescripcion().trim());
        feedback.setFeedbackRating(dto.getFeedbackRating());
        feedback.setFeedbackFecha(OffsetDateTime.now());
        feedback.setUsuario(usuario);

        Feedback guardado = feedbackRepository.save(feedback);
        return toResponseDTO(guardado);
    }

    @Override
    public void borrar(Long id) {
        if (!feedbackRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback no encontrado");
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<FeedbackResponseDTO> findAll() {
        List<Feedback> lista = feedbackRepository.findAll();
        if (lista.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay feedbacks disponibles");

        return lista.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public FeedbackResponseDTO findById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback no encontrado"));
        return toResponseDTO(feedback);
    }

    @Override
    public List<FeedbackResponseDTO> findByUsuarioid(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Feedback> lista = feedbackRepository.findByUsuario(usuario);
        if (lista.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay feedbacks para este usuario");

        return lista.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<FeedbackResponseDTO> findByFeedbacktipo(FeedbackTipo tipo) {
        List<Feedback> lista = feedbackRepository.findByFeedbackTipo(tipo);
        if (lista.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay feedbacks de este tipo");

        return lista.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private FeedbackResponseDTO toResponseDTO(Feedback f) {
        FeedbackResponseDTO dto = new FeedbackResponseDTO();
        dto.setFeedbackId(f.getFeedbackId());
        dto.setFeedbackTipo(f.getFeedbackTipo());
        dto.setFeedbackDescripcion(f.getFeedbackDescripcion());
        dto.setFeedbackRating(f.getFeedbackRating());
        dto.setFeedbackFecha(f.getFeedbackFecha());

        if (f.getUsuario() != null) {
            dto.setUsuarioId(f.getUsuario().getUsuarioId());
            dto.setUsuarioNombre(f.getUsuario().getUsuarioNombre());
        }

        return dto;
    }
}

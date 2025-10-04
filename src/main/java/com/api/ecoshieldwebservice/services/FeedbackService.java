package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.entities.Feedback;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IFeedbackService;
import com.api.ecoshieldwebservice.repositories.FeedbackRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public FeedbackResponseDTO findById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback no encontrado"));
        return modelMapper.map(feedback, FeedbackResponseDTO.class);
    }

    @Override
    public FeedbackResponseDTO registrar(FeedbackRequestDTO dto) {
        if (dto.getFeedbackDescripcion() != null && dto.getFeedbackDescripcion().length() > 300) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción excede el límite de caracteres");
        }

        Usuario u = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Feedback feedback = modelMapper.map(dto, Feedback.class);
        feedback.setUsuario(u);
        feedback.setFeedbackFecha(OffsetDateTime.now());

        feedback = feedbackRepository.save(feedback);
        return modelMapper.map(feedback, FeedbackResponseDTO.class);
    }

    @Override
    public void borrar(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback no encontrado");
        }
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<FeedbackResponseDTO> findAll() {
        List<Feedback> lista = feedbackRepository.findAll();
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay feedbacks disponibles");
        }
        return lista.stream().map(f -> modelMapper.map(f, FeedbackResponseDTO.class)).toList();
    }

    @Override
    public List<FeedbackResponseDTO> findByUsuarioid(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Feedback> lista = feedbackRepository.findByUsuario(usuario);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay feedbacks para este usuario");
        }

        return lista.stream().map(f -> modelMapper.map(f, FeedbackResponseDTO.class)).toList();
    }

    @Override
    public List<FeedbackResponseDTO> findByFeedbacktipo(String tipo) {
        List<Feedback> lista = feedbackRepository.findByFeedbackTipo(tipo);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay feedbacks de este tipo");
        }
        return lista.stream().map(f -> modelMapper.map(f, FeedbackResponseDTO.class)).toList();
    }
}

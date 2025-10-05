package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.enums.FeedbackTipo;

import java.util.List;

public interface IFeedbackService {
    FeedbackResponseDTO registrar(FeedbackRequestDTO dto, String correo);
    FeedbackResponseDTO findById(Long id);
    void borrar(Long id);
    List<FeedbackResponseDTO> findAll();
    List<FeedbackResponseDTO> findByUsuarioid(Long usuarioId);
    List<FeedbackResponseDTO> findByFeedbacktipo(FeedbackTipo tipo);
}

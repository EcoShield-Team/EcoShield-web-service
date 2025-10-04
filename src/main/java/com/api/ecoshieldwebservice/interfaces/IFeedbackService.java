package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.FeedbackResponseDTO;

import java.util.List;

public interface IFeedbackService {
    FeedbackResponseDTO findById(Long id);
    FeedbackResponseDTO registrar(FeedbackRequestDTO dto);
    void borrar(Long id);
    List<FeedbackResponseDTO> findAll();
    List<FeedbackResponseDTO> findByUsuarioid(Long usuarioId);
    List<FeedbackResponseDTO> findByFeedbacktipo(String tipo);
}

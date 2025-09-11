package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.FeedbackResponseDTO;

import java.util.List;

public interface IFeedbackServices {
    public FeedbackResponseDTO findById(Integer id);
    public FeedbackResponseDTO registrar(FeedbackRequestDTO dto);
    public void borrar(Integer id);
    public List<FeedbackResponseDTO> findAll();
    public List<FeedbackResponseDTO> findByUsuarioid_Id(Integer usuarioId);
    public List<FeedbackResponseDTO> findByFeedbacktipo(String tipo);
}

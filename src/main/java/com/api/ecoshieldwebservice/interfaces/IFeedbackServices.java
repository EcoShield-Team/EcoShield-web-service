package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.FeedbackRequestDTO;
import com.api.ecoshieldwebservice.dtos.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;

import java.util.List;

public interface IFeedbackServices {
    public FeedbackResponseDTO findById(Long id);
    public FeedbackResponseDTO registrar(FeedbackRequestDTO dto);
    public void borrar(Long id);
    public List<FeedbackResponseDTO> findAll();
    public List<FeedbackResponseDTO> findByUsuarioid(Usuario usuarioId);
    public List<FeedbackResponseDTO> findByFeedbacktipo(String tipo);
}

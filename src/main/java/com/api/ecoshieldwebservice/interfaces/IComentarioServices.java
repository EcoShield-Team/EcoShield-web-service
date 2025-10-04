package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.ComentarioResponseDTO;

import java.util.List;

public interface IComentarioServices {
    ComentarioResponseDTO registrar(ComentarioRequestDTO comentarioRequestDTO);
    ComentarioResponseDTO actualizar(Long postId, Long comentarioid, ComentarioRequestDTO dto);
    ComentarioResponseDTO findById(Long comentarioid);
    List<ComentarioResponseDTO> findAll();
    void borrar(Long postId, Long comentarioId);
    List<ComentarioResponseDTO> findByPostId(Long postId);
    List<ComentarioResponseDTO> findByUsuarioid(Long usuarioId);
}

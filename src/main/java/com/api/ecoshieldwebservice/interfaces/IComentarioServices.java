package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;

import java.util.List;

public interface IComentarioServices {
    public ComentarioResponseDTO registrar(ComentarioRequestDTO comentarioRequestDTO);
    public ComentarioResponseDTO actualizar(Long postId, Long comentarioid, ComentarioRequestDTO dto);
    public ComentarioResponseDTO findById(Long comentarioid);
    public List<ComentarioResponseDTO> findAll();
    public boolean borrar(Long postId, Long comentarioId);
    public List<ComentarioResponseDTO> findByPostId(Long postId);
    public List<ComentarioResponseDTO> findByUsuarioid(Long usuarioId);
}

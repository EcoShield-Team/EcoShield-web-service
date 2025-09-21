package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;

import java.util.List;

public interface IComentarioServices {
    public ComentarioResponseDTO registrar(ComentarioRequestDTO comentarioRequestDTO);
    public ComentarioResponseDTO actualizar(Integer postId, Integer comentarioid, ComentarioRequestDTO dto);
    public ComentarioResponseDTO findById(Integer comentarioid);
    public List<ComentarioResponseDTO> findAll();
    public void borrar(Integer postId, Integer comentarioId);
    public List<ComentarioResponseDTO> findByPostId(Integer postId);
    public List<ComentarioResponseDTO> findByUsuarioid(Integer usuarioId);
}

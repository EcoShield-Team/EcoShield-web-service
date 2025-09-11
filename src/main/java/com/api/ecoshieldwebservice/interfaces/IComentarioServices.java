package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.entities.Comentario;

import java.util.List;

public interface IComentarioServices {
    public ComentarioResponseDTO registrarComentario(ComentarioRequestDTO comentarioRequestDTO);
    public ComentarioResponseDTO actualizarComentario(ComentarioRequestDTO comentarioRequestDTO);
    public ComentarioResponseDTO findById(Integer id);
    public List<ComentarioResponseDTO> findAll();
    public void borrarComentario(Integer id);
}

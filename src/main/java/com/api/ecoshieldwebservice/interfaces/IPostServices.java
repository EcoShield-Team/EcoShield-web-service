package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.dtos.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;

import java.util.List;

public interface IPostServices {
    public PostResponseDTO registrar(PostRequestDTO dto);
    public PostResponseDTO actualizar(Integer id, PostRequestDTO dto);
    public List<PostResponseDTO> findByPosttitulo(String titulo);
    public List<PostResponseDTO> findAll();
    public PostResponseDTO findById(Integer id);
    public List<PostResponseDTO> findByUsuarioid_Id(Integer usuarioId);
    public void borrar(Integer id);
}

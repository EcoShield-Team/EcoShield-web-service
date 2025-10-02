package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;

import java.util.List;

public interface IPostServices {
    public PostResponseDTO registrar(PostRequestDTO dto);
    public PostResponseDTO actualizar(Long id, PostRequestDTO dto);
    public List<PostResponseDTO> findByPosttitulo(String titulo);
    public List<PostResponseDTO> findAll();
    public PostResponseDTO findById(Long id);
    public List<PostResponseDTO> findByUsuarioid(Usuario usuarioId);
    public void borrar(Long id);
}

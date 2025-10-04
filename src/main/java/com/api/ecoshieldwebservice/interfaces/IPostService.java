package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;

import java.util.List;

public interface IPostService {
    PostResponseDTO registrar(PostRequestDTO dto);
    PostResponseDTO actualizar(Long id, PostRequestDTO dto);
    List<PostResponseDTO> findByPosttitulo(String titulo);
    List<PostResponseDTO> findAll();
    PostResponseDTO findById(Long id);
    List<PostResponseDTO> findByUsuarioid(Long usuarioId);
    void borrar(Long id);
    boolean esAutorDelPost(Long postId, String correo);
}

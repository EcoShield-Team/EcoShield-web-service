package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPostServices {
    PostResponseDTO registrar(PostRequestDTO dto, MultipartFile file) throws IOException;
    PostResponseDTO actualizar(Long id, PostRequestDTO dto, MultipartFile file) throws IOException;
    List<PostResponseDTO> findByPosttitulo(String titulo);
    List<PostResponseDTO> findAll();
    PostResponseDTO findById(Long id);
    List<PostResponseDTO> findByUsuarioid(Usuario usuarioId);
    void borrar(Long id);
    byte[] obtenerImagenPorId(Long id);
}

package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface IPostService {
    PostResponseDTO registrar(PostRequestDTO dto, String correo);
    PostResponseDTO actualizar(Long id, PostRequestDTO dto, String correo);
    List<PostResponseDTO> findByPosttitulo(String titulo);
    List<PostResponseDTO> findAll();
    PostResponseDTO findById(Long id);
    List<PostResponseDTO> findByUsuarioid(Long usuarioId);
    void borrar(Long id, String correo, Collection<? extends GrantedAuthority> roles);
    boolean esAutorDelPost(Long postId, String correo);
    List<PostResponseDTO> findByCorreo(String correo);
}

package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.ComentarioResponseDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface IComentarioService {
    ComentarioResponseDTO registrar(ComentarioRequestDTO dto, String correo);
    ComentarioResponseDTO actualizar(Long postId, Long comentarioId, ComentarioRequestDTO dto, String correo);
    ComentarioResponseDTO findById(Long comentarioid);
    List<ComentarioResponseDTO> findAll();
    void borrar(Long postId, Long comentarioId, String correo, Collection<? extends GrantedAuthority> roles);
    List<ComentarioResponseDTO> findByPostId(Long postId, String correoActual);
    List<ComentarioResponseDTO> findByUsuarioid(Long usuarioId);
    boolean esAutorDelComentario(Long comentarioId, String correo);
}

package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.SearchResponseDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface IPostService {
    PostResponseDTO registrar(PostRequestDTO dto, MultipartFile imagen, String correo);
    PostResponseDTO actualizar(Long id, PostRequestDTO dto, MultipartFile imagen, String correo);
    List<PostResponseDTO> findAll(String correoActual);
    PostResponseDTO findById(Long id, String correoActual);
    SearchResponseDTO buscar(String query, String tipo, String correo);
    List<PostResponseDTO> findByUsuarioid(Long usuarioId, String correoActual);
    void borrar(Long id, String correo, Collection<? extends GrantedAuthority> roles);
    boolean esAutorDelPost(Long postId, String correo);
    List<PostResponseDTO> findByCorreo(String correo);
}

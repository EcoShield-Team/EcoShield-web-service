package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseForoDTO;
import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IComentarioService;
import com.api.ecoshieldwebservice.repositories.ComentarioLikeRepository;
import com.api.ecoshieldwebservice.repositories.ComentarioRepository;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class ComentarioService implements IComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioLikeRepository comentarioLikeRepository;


    @Override
    public ComentarioResponseDTO registrar(ComentarioRequestDTO dto, String correo) {
        validarComentario(dto);

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Comentario comentario = new Comentario();
        comentario.setComentarioTexto(dto.getComentarioTexto());
        comentario.setComentarioFecha(OffsetDateTime.now());
        comentario.setPost(post);
        comentario.setUsuario(usuario);

        Comentario guardado = comentarioRepository.save(comentario);
        return convertirAComentarioResponseDTO(guardado, correo);
    }

    @Override
    public ComentarioResponseDTO actualizar(Long postId, Long comentarioId, ComentarioRequestDTO dto, String correo) {
        validarComentario(dto);

        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario no encontrado"));

        if (!comentario.getPost().getPostId().equals(postId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario no pertenece al post indicado");
        }

        if (!esAutorDelComentario(comentarioId, correo)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar este comentario");
        }

        comentario.setComentarioTexto(dto.getComentarioTexto());
        Comentario actualizado = comentarioRepository.save(comentario);
        return convertirAComentarioResponseDTO(actualizado, correo);
    }

    @Override
    public void borrar(Long postId, Long comentarioId, String correo, Collection<? extends GrantedAuthority> roles) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario no encontrado"));

        if (!comentario.getPost().getPostId().equals(postId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario no pertenece al post indicado");
        }

        boolean esAutor = esAutorDelComentario(comentarioId, correo);
        boolean esAdmin = roles.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!esAutor && !esAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para eliminar este comentario");
        }

        comentarioRepository.delete(comentario);
    }

    @Override
    public ComentarioResponseDTO findById(Long comentarioId) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario no encontrado"));
        return convertirAComentarioResponseDTO(comentario, null);
    }

    @Override
    public List<ComentarioResponseDTO> findAll() {
        List<Comentario> lista = comentarioRepository.findAllByOrderByComentarioFechaDesc();
        return lista.stream().map(c -> convertirAComentarioResponseDTO(c, null)).toList();
    }

    @Override
    public List<ComentarioResponseDTO> findByPostId(Long postId, String correoActual) {
        List<Comentario> lista = comentarioRepository.findByPost_PostIdOrderByComentarioFechaDesc(postId);
        return lista.stream().map(c -> convertirAComentarioResponseDTO(c, correoActual)).toList();
    }

    @Override
    public List<ComentarioResponseDTO> findByUsuarioid(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Comentario> lista = comentarioRepository.findByUsuario(usuario);
        return lista.stream().map(c -> convertirAComentarioResponseDTO(c, null)).toList();
    }

    private void validarComentario(ComentarioRequestDTO dto) {
        if (dto.getComentarioTexto() == null || dto.getComentarioTexto().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario no puede estar vacío");
        }
    }

    @Override
    public boolean esAutorDelComentario(Long comentarioId, String correo) {
        return comentarioRepository.existsByComentarioIdAndUsuario_UsuarioCorreo(comentarioId, correo);
    }

    private ComentarioResponseDTO convertirAComentarioResponseDTO(Comentario comentario, String correoActual) {

        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setComentarioId(comentario.getComentarioId());
        dto.setComentarioTexto(comentario.getComentarioTexto());
        dto.setComentarioFecha(comentario.getComentarioFecha());
        int totalLikes = comentarioLikeRepository.countByComentario_ComentarioId(comentario.getComentarioId());
        dto.setLikeCount(totalLikes);
        if (correoActual != null) {
            boolean userLiked = comentarioLikeRepository.existsByComentario_ComentarioIdAndUsuario_UsuarioCorreo(comentario.getComentarioId(), correoActual);
            dto.setUserLiked(userLiked);
        } else { dto.setUserLiked(false);}

        Usuario usuario = comentario.getUsuario();
        if (usuario != null) {
            UsuarioResponseForoDTO uDto = new UsuarioResponseForoDTO();
            uDto.setUsuarioId(usuario.getUsuarioId());
            uDto.setUsuarioNombre(usuario.getUsuarioNombre());
            uDto.setUsuarioFotoPerfil(usuario.getUsuarioFotoPerfil());
            uDto.setUsuarioPais(usuario.getUsuarioPais());
            dto.setUsuario(uDto);
        }

        return dto;
    }

}

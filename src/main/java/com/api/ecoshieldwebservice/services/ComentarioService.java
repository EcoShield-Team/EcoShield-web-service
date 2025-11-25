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
    public ComentarioResponseDTO registrar(Long postId, ComentarioRequestDTO dto, String correo) {

        if (dto.getComentarioTexto() == null || dto.getComentarioTexto().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario no puede estar vacío");
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));


        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Comentario comentario = new Comentario();
        comentario.setComentarioTexto(dto.getComentarioTexto());
        comentario.setPost(post);
        comentario.setUsuario(usuario);

        Comentario guardado = comentarioRepository.save(comentario);
        return convertirAComentarioResponseDTO(guardado, correo);
    }

    @Override
    public ComentarioResponseDTO actualizar(Long postId, Long comentarioId, ComentarioRequestDTO dto, String correo) {

        if (dto.getComentarioTexto() == null || dto.getComentarioTexto().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario no puede estar vacío");
        }

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
        boolean esAutorDelPost = comentario.getPost().getUsuario().getUsuarioCorreo().equals(correo);

        if (!esAutor && !esAdmin && !esAutorDelPost) {
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
    public List<ComentarioResponseDTO> findByUsuarioid(Long usuarioId, String correoActual) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Comentario> lista = comentarioRepository.findByUsuario(usuario);
        return lista.stream().map(c -> convertirAComentarioResponseDTO(c, correoActual)).toList();
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
        dto.setComentarioFechaModificacion(comentario.getComentarioFechaModificacion());
        dto.setEditado(comentario.getComentarioFechaModificacion() != null);
        dto.setPostId(comentario.getPost().getPostId());

        int totalLikes = comentarioLikeRepository.countByComentario_ComentarioId(comentario.getComentarioId());
        dto.setLikeCount(totalLikes);

        if (correoActual != null) {
            boolean userLiked = comentarioLikeRepository.existsByComentario_ComentarioIdAndUsuario_UsuarioCorreo(comentario.getComentarioId(), correoActual);
            dto.setUserLiked(userLiked);
        } else { dto.setUserLiked(false);}

        Usuario usuario = comentario.getUsuario();
        UsuarioResponseForoDTO u = new UsuarioResponseForoDTO();
        u.setUsuarioId(usuario.getUsuarioId());
        u.setUsuarioNombre(usuario.getUsuarioNombre());
        u.setUsuarioFotoPerfil(usuario.getUsuarioFotoPerfil());
        u.setUsuarioPais(usuario.getUsuarioPais());
        dto.setUsuario(u);

        return dto;
    }

}

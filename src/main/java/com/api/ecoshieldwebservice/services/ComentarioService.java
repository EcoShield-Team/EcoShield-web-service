package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseForoDTO;
import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IComentarioService;
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
        return convertirAComentarioResponseDTO(guardado);
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
        return convertirAComentarioResponseDTO(actualizado);
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
        return convertirAComentarioResponseDTO(comentario);
    }

    @Override
    public List<ComentarioResponseDTO> findAll() {
        List<Comentario> lista = comentarioRepository.findAll();
        return lista.stream().map(this::convertirAComentarioResponseDTO).toList();
    }

    @Override
    public List<ComentarioResponseDTO> findByPostId(Long postId) {
        List<Comentario> lista = comentarioRepository.findByPost_PostIdOrderByComentarioFechaAsc(postId);
        return lista.stream().map(this::convertirAComentarioResponseDTO).toList();
    }

    @Override
    public List<ComentarioResponseDTO> findByUsuarioid(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Comentario> lista = comentarioRepository.findByUsuario(usuario);
        return lista.stream().map(this::convertirAComentarioResponseDTO).toList();
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

    private ComentarioResponseDTO convertirAComentarioResponseDTO(Comentario comentario) {
        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setComentarioId(comentario.getComentarioId());
        dto.setComentarioTexto(comentario.getComentarioTexto());
        dto.setComentarioFecha(comentario.getComentarioFecha());

        if (comentario.getUsuario() != null) {
            Usuario usuario = comentario.getUsuario();
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

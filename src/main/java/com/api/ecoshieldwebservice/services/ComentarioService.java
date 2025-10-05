package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IComentarioService;
import com.api.ecoshieldwebservice.repositories.ComentarioRepository;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;


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
        return modelMapper.map(guardado, ComentarioResponseDTO.class);
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
        return modelMapper.map(actualizado, ComentarioResponseDTO.class);
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
        return modelMapper.map(comentario, ComentarioResponseDTO.class);
    }

    @Override
    public List<ComentarioResponseDTO> findAll() {
        List<Comentario> lista = comentarioRepository.findAll();
        if (lista.isEmpty()) {
            return List.of();
        }
        return lista.stream().map(c -> modelMapper.map(c, ComentarioResponseDTO.class)).toList();
    }

    @Override
    public List<ComentarioResponseDTO> findByPostId(Long postId) {
        List<Comentario> lista = comentarioRepository.findByPost_PostIdOrderByComentarioFechaAsc(postId);
        if (lista.isEmpty()) {
            return List.of();
        }
        return lista.stream().map(c -> modelMapper.map(c, ComentarioResponseDTO.class)).toList();
    }

    @Override
    public List<ComentarioResponseDTO> findByUsuarioid(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Comentario> lista = comentarioRepository.findByUsuario(usuario);
        if (lista.isEmpty()) {
            return List.of();
        }
        return lista.stream().map(c -> modelMapper.map(c, ComentarioResponseDTO.class)).toList();
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
}

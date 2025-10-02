package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.UsuarioResponseForoDTO;
import com.api.ecoshieldwebservice.dtos.request.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IComentarioServices;
import com.api.ecoshieldwebservice.repositories.ComentarioRepository;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ComentarioService implements IComentarioServices {

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ModelMapper modelMapper;

    private ComentarioResponseDTO EtoRespDTO(Comentario comentario) {
        ComentarioResponseDTO dto = modelMapper.map(comentario, ComentarioResponseDTO.class);
        Usuario user = comentario.getUsuario();
        if (user != null) {
            UsuarioResponseForoDTO uDto = new UsuarioResponseForoDTO();
            uDto.setUsuarioId(user.getUsuarioId());
            uDto.setUsuarioNombre(user.getUsuarioNombre());
            uDto.setUsuarioFotoPerfil(user.getUsuarioFotoPerfil());
            uDto.setUsuarioPais(user.getUsuarioPais());
            dto.setUsuario(uDto);
        }
        return dto;
    }
    @Override
    public ComentarioResponseDTO registrar(ComentarioRequestDTO dto) {
        Comentario comentario = new Comentario();
        comentario.setComentarioTexto(dto.getComentarioTexto());
        comentario.setComentarioFecha(OffsetDateTime.now());
        Post p = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        comentario.setPost(p);
        Usuario u = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        comentario.setUsuario(u);
        comentario = comentarioRepository.save(comentario);
        return EtoRespDTO(comentario);
    }

    @Override
    public ComentarioResponseDTO actualizar(Long postId, Long comentarioid, ComentarioRequestDTO dto) {
        Comentario comentario = comentarioRepository.findById(comentarioid)
                .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));

        if (!comentario.getPost().getPostId().equals(postId)) {
            throw new EntityNotFoundException("El comentario no pertenece al post indicado");
        }

        comentario.setComentarioTexto(dto.getComentarioTexto());
        Comentario actualizado = comentarioRepository.save(comentario);
        return EtoRespDTO(actualizado);
    }

    @Override
    public ComentarioResponseDTO findById(Long comentarioid) {
        Comentario comentario = comentarioRepository.findById(comentarioid)
                .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));
        return EtoRespDTO(comentario);
    }

    @Override
    public List<ComentarioResponseDTO> findAll() {
        return comentarioRepository.findAll()
                .stream().map(this::EtoRespDTO).toList();
    }

    @Override
    public void borrar(Long postId, Long comentarioId) {
        Comentario c = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));
        if (!c.getPost().getPostId().equals(postId)) {
            throw new EntityNotFoundException("El comentario no pertenece al post indicado");
        }
        comentarioRepository.delete(c);
    }

    @Override
    public List<ComentarioResponseDTO> findByPostId(Long postId) {
        return comentarioRepository.findByPost_PostIdOrderByComentarioFechaAsc(postId)
                .stream().map(this::EtoRespDTO).toList();
    }

    @Override
    public List<ComentarioResponseDTO> findByUsuarioid(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        return comentarioRepository.findByUsuario(usuario)
                .stream()
                .map(this::EtoRespDTO)
                .toList();
    }
}

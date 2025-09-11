package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioUpdateDTO;
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
        Usuario user = comentario.getUsuarioid();
        if (user != null) {
            UsuarioUpdateDTO uDto = new UsuarioUpdateDTO();
            uDto.setUsuarioid(user.getUsuarioid());
            uDto.setUsuarionombre(user.getUsuarionombre());
            uDto.setUsuariofotoperfil(user.getUsuariofotoperfil());
            uDto.setUsuariopais(user.getUsuariopais());
            dto.setUsuario(uDto);
        }
        return dto;
    }
    @Override
    public ComentarioResponseDTO registrar(ComentarioRequestDTO dto) {
        Comentario comentario = new Comentario();
        comentario.setComentariotexto(dto.getComentariotexto());
        comentario.setComentariofecha(OffsetDateTime.now());
        Post p = postRepository.findById(dto.getPostid())
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        comentario.setPostid(p);
        Usuario u = usuarioRepository.findById(dto.getUsuarioid())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        comentario.setUsuarioid(u);
        comentario = comentarioRepository.save(comentario);
        return EtoRespDTO(comentario);
    }

    @Override
    public ComentarioResponseDTO actualizar(Integer comentarioid, ComentarioRequestDTO dto) {
        Comentario comentario = comentarioRepository.findById(comentarioid)
                .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));
        comentario.setComentariotexto(dto.getComentariotexto());
        Comentario actualizado = comentarioRepository.save(comentario);
        return EtoRespDTO(actualizado);
    }

    @Override
    public ComentarioResponseDTO findById(Integer comentarioid) {
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
    public void borrar(Integer comentarioid) {
        if (!comentarioRepository.existsById(comentarioid)) {
            throw new EntityNotFoundException("Comentario no encontrado");
        }
        comentarioRepository.deleteById(comentarioid);
    }

    @Override
    public List<ComentarioResponseDTO> findByPostId(Integer postId) {
        return comentarioRepository.findByPostid_PostidOrderByComentariofechaAsc(postId)
                .stream().map(this::EtoRespDTO).toList();
    }

    @Override
    public List<ComentarioResponseDTO> findByUsuarioid(Usuario usuarioId) {
        return comentarioRepository.findByUsuarioid(usuarioId)
                .stream()
                .map(this::EtoRespDTO)
                .toList();
    }
}

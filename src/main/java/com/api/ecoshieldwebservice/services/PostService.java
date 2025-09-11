package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioUpdateDTO;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IPostServices;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostServices {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    private PostResponseDTO EtoRespDTO(Post post) {
        PostResponseDTO dto = modelMapper.map(post, PostResponseDTO.class);
        Usuario u = post.getUsuarioid();
        if (u != null) {
            UsuarioUpdateDTO uDto = new UsuarioUpdateDTO();
            uDto.setUsuarioid(u.getUsuarioid());
            uDto.setUsuarionombre(u.getUsuarionombre());
            uDto.setUsuariofotoperfil(u.getUsuariofotoperfil());
            uDto.setUsuariopais(u.getUsuariopais());
            dto.setUsuario(uDto);
        }
        return dto;
    }

    @Override
    public PostResponseDTO registrar(PostRequestDTO dto) {
        Post post = modelMapper.map(dto, Post.class);
        Usuario u = usuarioRepository.findById(dto.getUsuarioid())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        post.setUsuarioid(u);
        post.setPostfecha(OffsetDateTime.now());
        post = postRepository.save(post);
        return EtoRespDTO(post);
    }

    @Override
    public PostResponseDTO actualizar(Integer id, PostRequestDTO dto) {
        Post existe = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        Usuario prop = existe.getUsuarioid();
        modelMapper.map(dto , existe);
        existe.setUsuarioid(prop);
        Post actualizado = postRepository.save(existe);
        return  EtoRespDTO(actualizado);
    }

    @Override
    public List<PostResponseDTO> findByPosttitulo(String titulo) {
        return postRepository.findByPosttitulo(titulo).stream()
                .map(this::EtoRespDTO)
                .toList();
    }

    @Override
    public List<PostResponseDTO> findAll() {
        return postRepository.findAll().stream()
                .map(this::EtoRespDTO).toList();
    }

    @Override
    public PostResponseDTO findById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        return EtoRespDTO(post);
    }

    @Override
    public List<PostResponseDTO> findByUsuarioid(Usuario usuarioId) {
        return postRepository.findByUsuarioid(usuarioId).stream()
                .map(this::EtoRespDTO).toList();
    }

    @Override
    public void borrar(Integer id) {
        if (!postRepository.existsById(id))
            throw new EntityNotFoundException("Feedback no encontrado");
        postRepository.deleteById(id);
    }
}

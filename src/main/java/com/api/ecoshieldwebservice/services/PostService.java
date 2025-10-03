package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.UsuarioResponseForoDTO;
import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Base64;
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
        Usuario u = post.getUsuario();
        if (u != null) {
            UsuarioResponseForoDTO uDto = new UsuarioResponseForoDTO();
            uDto.setUsuarioId(u.getUsuarioId());
            uDto.setUsuarioNombre(u.getUsuarioNombre());
            uDto.setUsuarioFotoPerfil(u.getUsuarioFotoPerfil());
            uDto.setUsuarioPais(u.getUsuarioPais());
            dto.setUsuario(uDto);
        }
        return dto;
    }

    @Override
    public PostResponseDTO registrar(PostRequestDTO dto, MultipartFile file) throws IOException {
        Post post = modelMapper.map(dto, Post.class);
        Usuario u = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        post.setUsuario(u);

        if (file != null && !file.isEmpty()) {
            post.setPostFoto(Base64.getEncoder().encodeToString(file.getBytes()));
        }

        post.setPostFecha(OffsetDateTime.now());
        post = postRepository.save(post);
        return EtoRespDTO(post);
    }

    @Override
    public PostResponseDTO actualizar(Long id, PostRequestDTO dto, MultipartFile file) throws IOException {
        Post existe = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        Usuario u = existe.getUsuario();
        modelMapper.map(dto, existe);
        existe.setUsuario(u);

        if (file != null && !file.isEmpty()) {
            existe.setPostFoto(Base64.getEncoder().encodeToString(file.getBytes()));
        }

        Post actualizado = postRepository.save(existe);
        return EtoRespDTO(actualizado);
    }

    @Override
    public List<PostResponseDTO> findByPosttitulo(String titulo) {
        return postRepository.findByPostTitulo(titulo).stream()
                .map(this::EtoRespDTO)
                .toList();
    }

    @Override
    public List<PostResponseDTO> findAll() {
        return postRepository.findAll().stream()
                .map(this::EtoRespDTO)
                .toList();
    }

    @Override
    public PostResponseDTO findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        return EtoRespDTO(post);
    }

    @Override
    public List<PostResponseDTO> findByUsuarioid(Usuario usuarioId) {
        return postRepository.findByUsuario(usuarioId).stream()
                .map(this::EtoRespDTO)
                .toList();
    }

    @Override
    public void borrar(Long id) {
        if (!postRepository.existsById(id))
            throw new EntityNotFoundException("Post no encontrado");
        postRepository.deleteById(id);
    }

    @Override
    public byte[] obtenerImagenPorId(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));

        if (post.getPostFoto() == null || post.getPostFoto().isEmpty()) {
            return null;
        }

        return Base64.getDecoder().decode(post.getPostFoto());
    }
}

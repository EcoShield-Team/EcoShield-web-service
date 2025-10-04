package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IPostServices;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    @Override
    public PostResponseDTO registrar(PostRequestDTO dto) {
        if (dto.getPostTitulo() == null || dto.getPostTitulo().isBlank()
                || dto.getPostDescripcion() == null || dto.getPostDescripcion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título y la descripción son obligatorios");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Post post = modelMapper.map(dto, Post.class);
        post.setUsuario(usuario);
        post.setPostFecha(OffsetDateTime.now());

        Post guardado = postRepository.save(post);
        return modelMapper.map(guardado, PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO actualizar(Long id, PostRequestDTO dto) {
        Post existente = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        if (dto.getPostTitulo() == null || dto.getPostTitulo().isBlank()
                || dto.getPostDescripcion() == null || dto.getPostDescripcion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título y la descripción son obligatorios");
        }

        Usuario usuario = existente.getUsuario();

        modelMapper.map(dto, existente);
        existente.setUsuario(usuario);
        existente.setPostFecha(OffsetDateTime.now());

        Post actualizado = postRepository.save(existente);
        return modelMapper.map(actualizado, PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> findByPosttitulo(String titulo) {
        List<Post> lista = postRepository.findByPostTitulo(titulo);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron posts con ese título");
        }
        return lista.stream().map(p -> modelMapper.map(p, PostResponseDTO.class)).toList();
    }

    @Override
    public List<PostResponseDTO> findAll() {
        List<Post> lista = postRepository.findAll();
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay posts disponibles");
        }
        return lista.stream().map(p -> modelMapper.map(p, PostResponseDTO.class)).toList();
    }

    @Override
    public PostResponseDTO findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));
        return modelMapper.map(post, PostResponseDTO.class);
    }

    @Override
    public List<PostResponseDTO> findByUsuarioid(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Post> lista = postRepository.findByUsuario(usuario);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay posts de este usuario");
        }

        return lista.stream().map(p -> modelMapper.map(p, PostResponseDTO.class)).toList();
    }

    @Override
    public void borrar(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado");
        }
        postRepository.deleteById(id);
    }
}

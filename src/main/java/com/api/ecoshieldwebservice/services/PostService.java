package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IPostService;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PostResponseDTO registrar(PostRequestDTO dto, String correo) {
        validarCampos(dto);

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Post post = modelMapper.map(dto, Post.class);
        post.setPostId(null);
        post.setUsuario(usuario);
        post.setPostFecha(OffsetDateTime.now());

        Post guardado = postRepository.save(post);
        return modelMapper.map(guardado, PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO actualizar(Long id, PostRequestDTO dto, String correo) {
        validarCampos(dto);

        Post existente = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        if (!esAutorDelPost(id, correo)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar este post");
        }

        modelMapper.map(dto, existente);
        existente.setPostFecha(OffsetDateTime.now());

        Post actualizado = postRepository.save(existente);
        return modelMapper.map(actualizado, PostResponseDTO.class);
    }

    @Override
    public void borrar(Long id, String correo, Collection<? extends GrantedAuthority> roles) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        boolean esAutor = esAutorDelPost(id, correo);
        boolean esAdmin = roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        if (!esAutor && !esAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para eliminar este post");
        }

        postRepository.delete(post);
    }

    @Override
    public List<PostResponseDTO> findByPosttitulo(String titulo) {
        List<Post> lista = postRepository.findByPostTitulo(titulo);
        if (lista.isEmpty()) {
            return List.of();
        }
        return lista.stream().map(p -> modelMapper.map(p, PostResponseDTO.class)).toList();
    }

    @Override
    public List<PostResponseDTO> findAll() {
        List<Post> lista = postRepository.findAll();
        if (lista.isEmpty()) {
            return List.of();
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
            return List.of();
        }

        return lista.stream().map(p -> modelMapper.map(p, PostResponseDTO.class)).toList();
    }

    private void validarCampos(PostRequestDTO dto) {
        if (dto.getPostTitulo() == null || dto.getPostTitulo().isBlank() ||
                dto.getPostDescripcion() == null || dto.getPostDescripcion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título y la descripción son obligatorios");
        }
    }

    @Override
    public List<PostResponseDTO> findByCorreo(String correo) {
        List<Post> lista = postRepository.listarPostsPorCorreo(correo);
        if (lista.isEmpty()) {
            return List.of();
        }
        return lista.stream()
                .map(post -> modelMapper.map(post, PostResponseDTO.class))
                .toList();
    }

    @Override
    public boolean esAutorDelPost(Long postId, String correo) {
        return postRepository.existsByPostIdAndUsuario_UsuarioCorreo(postId, correo);
    }
}

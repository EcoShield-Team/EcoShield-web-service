package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.ICloudinaryService;
import com.api.ecoshieldwebservice.interfaces.IPostService;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ICloudinaryService cloudinaryService;


    @Override
    public PostResponseDTO registrar(PostRequestDTO dto, MultipartFile imagen, String correo) {
        validarCampos(dto);

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Post post = new Post();
        post.setPostTitulo(dto.getPostTitulo());
        post.setPostDescripcion(dto.getPostDescripcion());
        post.setUsuario(usuario);
        post.setPostFecha(OffsetDateTime.now());

        if (imagen != null && !imagen.isEmpty()) {
            String url = cloudinaryService.uploadImage(imagen);
            post.setPostFoto(url);
        }

        Post guardado = postRepository.save(post);
        return convertirAPostResponseDTO(guardado);
    }

    @Override
    public PostResponseDTO actualizar(Long id, PostRequestDTO dto, MultipartFile imagen, String correo) {
        validarCampos(dto);

        Post existente = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        if (!esAutorDelPost(id, correo)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar este post");
        }

        existente.setPostTitulo(dto.getPostTitulo());
        existente.setPostDescripcion(dto.getPostDescripcion());
        existente.setPostFecha(OffsetDateTime.now());

        if (imagen != null && !imagen.isEmpty()) {
            String url = cloudinaryService.uploadImage(imagen);
            existente.setPostFoto(url);
        }

        Post actualizado = postRepository.save(existente);
        return convertirAPostResponseDTO(actualizado);
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
        return lista.stream().map(this::convertirAPostResponseDTO).toList();
    }

    @Override
    public List<PostResponseDTO> findAll() {
        List<Post> lista = postRepository.findAllByOrderByPostFechaDesc();
        if (lista.isEmpty()) {
            return List.of();
        }
        return lista.stream().map(this::convertirAPostResponseDTO).toList();
    }

    @Override
    public PostResponseDTO findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));
        return convertirAPostResponseDTO(post);
    }

    @Override
    public List<PostResponseDTO> findByUsuarioid(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Post> lista = postRepository.findByUsuario(usuario);
        if (lista.isEmpty()) {
            return List.of();
        }

        return lista.stream().map(this::convertirAPostResponseDTO).toList();
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
        return lista.stream().map(this::convertirAPostResponseDTO).toList();
    }

    @Override
    public boolean esAutorDelPost(Long postId, String correo) {
        return postRepository.existsByPostIdAndUsuario_UsuarioCorreo(postId, correo);
    }

    private PostResponseDTO convertirAPostResponseDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setPostId(post.getPostId());
        dto.setPostTitulo(post.getPostTitulo());
        dto.setPostDescripcion(post.getPostDescripcion());
        dto.setPostFoto(post.getPostFoto());
        dto.setPostFecha(post.getPostFecha());

        if (post.getUsuario() != null) {
            var usuarioDTO = new com.api.ecoshieldwebservice.dtos.user.UsuarioResponseForoDTO();
            usuarioDTO.setUsuarioId(post.getUsuario().getUsuarioId());
            usuarioDTO.setUsuarioNombre(post.getUsuario().getUsuarioNombre());
            usuarioDTO.setUsuarioFotoPerfil(post.getUsuario().getUsuarioFotoPerfil());
            usuarioDTO.setUsuarioPais(post.getUsuario().getUsuarioPais());
            dto.setUsuario(usuarioDTO);
        }

        return dto;
    }

}

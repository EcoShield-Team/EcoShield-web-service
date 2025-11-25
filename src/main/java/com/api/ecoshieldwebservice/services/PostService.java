package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.SearchResponseDTO;
import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseForoDTO;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.ICloudinaryService;
import com.api.ecoshieldwebservice.interfaces.IPostService;
import com.api.ecoshieldwebservice.repositories.ComentarioRepository;
import com.api.ecoshieldwebservice.repositories.PostLikeRepository;
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
    private PostLikeRepository postLikeRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

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
        post.setPostFechaModificacion(null);

        if (imagen != null && !imagen.isEmpty()) {
            String url = cloudinaryService.uploadImage(imagen);
            post.setPostFoto(url);
        }

        Post guardado = postRepository.save(post);
        return convertirAPostResponseDTO(guardado, correo);
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
        existente.setPostFechaModificacion(OffsetDateTime.now());

        if (imagen != null && !imagen.isEmpty()) {
            String url = cloudinaryService.uploadImage(imagen);
            existente.setPostFoto(url);
        }

        Post actualizado = postRepository.save(existente);
        return convertirAPostResponseDTO(actualizado, correo);
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
    public List<PostResponseDTO> findAll(String correoActual) {
        List<Post> lista = postRepository.findAllByOrderByPostFechaDesc();
        return lista.stream().map(p -> convertirAPostResponseDTO(p, correoActual)).toList();
    }

    @Override
    public PostResponseDTO findById(Long id, String correoActual) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));
        return convertirAPostResponseDTO(post, correoActual);
    }

    @Override
    public SearchResponseDTO buscar(String query, String tipo, String correo) {

        SearchResponseDTO respuesta = new SearchResponseDTO();

        switch (tipo.toLowerCase()) {
            case "destacado":
                List<Post> destacados = postRepository.buscarPostsDestacados(query);
                respuesta.setPosts(destacados.stream()
                                .map(p -> convertirAPostResponseDTO(p, correo)).toList());
                break;

            case "recientes":
                List<Post> recientes = postRepository.buscarPostsRecientes(query);
                respuesta.setPosts(recientes.stream()
                                .map(p -> convertirAPostResponseDTO(p, correo)).toList());
                break;

            case "personas":
                List<Usuario> usuarios = usuarioRepository.buscarUsuariosPorNombre(query);
                respuesta.setUsuarios(
                        usuarios.stream().map(u -> {
                            UsuarioResponseForoDTO dto = new UsuarioResponseForoDTO();
                            dto.setUsuarioId(u.getUsuarioId());
                            dto.setUsuarioNombre(u.getUsuarioNombre());
                            dto.setUsuarioFotoPerfil(u.getUsuarioFotoPerfil());
                            dto.setUsuarioPais(u.getUsuarioPais());
                            return dto;
                        }).toList());
                break;

            default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de búsqueda inválido: " + tipo);
        }
        return respuesta;
    }

    @Override
    public List<PostResponseDTO> findByUsuarioid(Long usuarioId, String correoActual)  {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Post> lista = postRepository.findByUsuario(usuario);
        return lista.stream().map(p -> convertirAPostResponseDTO(p, correoActual)).toList();
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
        return lista.stream().map(p -> convertirAPostResponseDTO(p, correo)).toList();
    }

    @Override
    public boolean esAutorDelPost(Long postId, String correo) {
        return postRepository.existsByPostIdAndUsuario_UsuarioCorreo(postId, correo);
    }

    private PostResponseDTO convertirAPostResponseDTO(Post post, String correoActual) {

        PostResponseDTO dto = new PostResponseDTO();

        dto.setPostId(post.getPostId());
        dto.setPostTitulo(post.getPostTitulo());
        dto.setPostDescripcion(post.getPostDescripcion());
        dto.setPostFoto(post.getPostFoto());
        dto.setPostFecha(post.getPostFecha());
        dto.setPostFechaModificacion(post.getPostFechaModificacion());
        dto.setEditado(post.getPostFechaModificacion() != null);

        dto.setLikeCount(postLikeRepository.countByPost_PostId(post.getPostId()));
        if (correoActual != null) {
            dto.setUserLiked(postLikeRepository.existsByPost_PostIdAndUsuario_UsuarioCorreo(post.getPostId(), correoActual));
        } else { dto.setUserLiked(false);}

        dto.setCommentCount(comentarioRepository.countByPost_PostId(post.getPostId()));


        if (post.getUsuario() != null) {
            UsuarioResponseForoDTO userDTO = new UsuarioResponseForoDTO();
            userDTO.setUsuarioId(post.getUsuario().getUsuarioId());
            userDTO.setUsuarioNombre(post.getUsuario().getUsuarioNombre());
            userDTO.setUsuarioFotoPerfil(post.getUsuario().getUsuarioFotoPerfil());
            userDTO.setUsuarioPais(post.getUsuario().getUsuarioPais());
            dto.setUsuario(userDTO);
        }

        return dto;
    }
}

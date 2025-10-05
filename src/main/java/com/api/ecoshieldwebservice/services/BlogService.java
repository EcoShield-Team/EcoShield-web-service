package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;
import com.api.ecoshieldwebservice.entities.Blog;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.BlogTipo;
import com.api.ecoshieldwebservice.interfaces.IBlogService;
import com.api.ecoshieldwebservice.repositories.BlogRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public BlogResponseDTO registrar(BlogRequestDTO dto, String correo) {
        validarCampos(dto);

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Blog blog = new Blog();
        blog.setBlogTipo(dto.getBlogTipo());
        blog.setBlogTitulo(dto.getBlogTitulo());
        blog.setBlogDescripcion(dto.getBlogDescripcion());
        blog.setBlogImagen(dto.getBlogImagen());
        blog.setBlogEstado(dto.getBlogEstado());
        blog.setUsuario(usuario);
        blog.setBlogFechaPublicacion(OffsetDateTime.now());

        Blog guardado = blogRepository.save(blog);
        return toResponseDTO(guardado);
    }

    @Override
    public BlogResponseDTO actualizar(Long id, BlogRequestDTO dto, String correo) {
        validarCampos(dto);

        Blog existente = blogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado"));

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        existente.setBlogTipo(dto.getBlogTipo());
        existente.setBlogTitulo(dto.getBlogTitulo());
        existente.setBlogDescripcion(dto.getBlogDescripcion());
        existente.setBlogImagen(dto.getBlogImagen());
        existente.setBlogEstado(dto.getBlogEstado());
        existente.setUsuario(usuario);
        existente.setBlogFechaPublicacion(OffsetDateTime.now());

        Blog actualizado = blogRepository.save(existente);
        return toResponseDTO(actualizado);
    }

    @Override
    public void borrar(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado"));
        blogRepository.delete(blog);
    }

    @Override
    public List<BlogResponseDTO> findAll() {
        return blogRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public BlogResponseDTO findTipDelDia() {
        Blog blog = blogRepository.findFirstByBlogTipoOrderByBlogFechaPublicacionDesc(BlogTipo.TIP);
        if (blog == null)
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay tip del día disponible");
        return toResponseDTO(blog);
    }

    @Override
    public List<BlogResponseDTO> findAllNews() {
        return blogRepository.findAllNews().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public BlogResponseDTO findById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado"));
        return toResponseDTO(blog);
    }

    private void validarCampos(BlogRequestDTO dto) {
        if (dto.getBlogTitulo() == null || dto.getBlogTitulo().isBlank() ||
                dto.getBlogDescripcion() == null || dto.getBlogDescripcion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título y descripción son obligatorios");
        }
    }

    private BlogResponseDTO toResponseDTO(Blog blog) {
        BlogResponseDTO dto = new BlogResponseDTO();
        dto.setBlogId(blog.getBlogId());
        dto.setUsuarioId(blog.getUsuario() != null ? blog.getUsuario().getUsuarioId() : null);
        dto.setBlogTipo(blog.getBlogTipo());
        dto.setBlogTitulo(blog.getBlogTitulo());
        dto.setBlogDescripcion(blog.getBlogDescripcion());
        dto.setBlogImagen(blog.getBlogImagen());
        dto.setBlogEstado(blog.getBlogEstado());
        dto.setBlogFechaPublicacion(blog.getBlogFechaPublicacion());
        return dto;
    }
}

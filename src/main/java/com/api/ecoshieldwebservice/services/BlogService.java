package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;
import com.api.ecoshieldwebservice.entities.Blog;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.BlogTipo;
import com.api.ecoshieldwebservice.interfaces.IBlogServices;
import com.api.ecoshieldwebservice.repositories.BlogRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BlogService implements IBlogServices {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BlogResponseDTO findById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado"));
        return modelMapper.map(blog, BlogResponseDTO.class);
    }

    @Override
    public BlogResponseDTO registrar(BlogRequestDTO blogRequestDTO) {
        if (blogRequestDTO.getBlogTitulo() == null || blogRequestDTO.getBlogTitulo().isBlank()
                || blogRequestDTO.getBlogDescripcion() == null || blogRequestDTO.getBlogDescripcion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título y contenido son obligatorios");
        }

        Usuario usuario = usuarioRepository.findById(blogRequestDTO.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Blog blog = modelMapper.map(blogRequestDTO, Blog.class);
        blog.setUsuario(usuario);
        blog.setBlogFechaPublicacion(OffsetDateTime.now());

        Blog guardado = blogRepository.save(blog);
        return modelMapper.map(guardado, BlogResponseDTO.class);
    }

    @Override
    public BlogResponseDTO actualizar(Long id, BlogRequestDTO blogRequestDTO) {
        Blog blogExistente = blogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado"));

        if (blogRequestDTO.getBlogTitulo() == null || blogRequestDTO.getBlogTitulo().isBlank()
                || blogRequestDTO.getBlogDescripcion() == null || blogRequestDTO.getBlogDescripcion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título y contenido son obligatorios");
        }

        Usuario usuario = usuarioRepository.findById(blogRequestDTO.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        modelMapper.map(blogRequestDTO, blogExistente);

        blogExistente.setUsuario(usuario);
        blogExistente.setBlogFechaPublicacion(OffsetDateTime.now());

        Blog actualizado = blogRepository.save(blogExistente);
        return modelMapper.map(actualizado, BlogResponseDTO.class);
    }


    @Override
    public void borrar(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado");
        }
        blogRepository.deleteById(id);
    }

    @Override
    public List<BlogResponseDTO> findAll() {
        List<Blog> lista = blogRepository.findAll();
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay blogs disponibles");
        }
        return lista.stream()
                .map(blog -> modelMapper.map(blog, BlogResponseDTO.class))
                .toList();
    }

    @Override
    public BlogResponseDTO findTipDelDia() {
        Blog blog = blogRepository.findFirstByBlogTipoOrderByBlogFechaPublicacionDesc(BlogTipo.TIP);
        if (blog == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay tip del día disponible");
        }
        return modelMapper.map(blog, BlogResponseDTO.class);
    }

    @Override
    public List<BlogResponseDTO> findAllNews() {
        List<Blog> lista = blogRepository.findAllNews();
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay noticias disponibles");
        }
        return lista.stream()
                .map(blog -> modelMapper.map(blog, BlogResponseDTO.class))
                .toList();
    }
}

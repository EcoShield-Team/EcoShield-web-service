package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;
import com.api.ecoshieldwebservice.entities.Blog;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.BlogTipo;
import com.api.ecoshieldwebservice.interfaces.IBlogService;
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
public class BlogService implements IBlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BlogResponseDTO registrar(BlogRequestDTO dto, String correo) {
        validarCampos(dto);

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Blog blog = modelMapper.map(dto, Blog.class);
        blog.setUsuario(usuario);
        blog.setBlogFechaPublicacion(OffsetDateTime.now());

        Blog guardado = blogRepository.save(blog);
        return modelMapper.map(guardado, BlogResponseDTO.class);
    }

    @Override
    public BlogResponseDTO actualizar(Long id, BlogRequestDTO dto, String correo) {
        validarCampos(dto);

        Blog existente = blogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado"));

        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        modelMapper.map(dto, existente);
        existente.setUsuario(usuario);
        existente.setBlogFechaPublicacion(OffsetDateTime.now());

        Blog actualizado = blogRepository.save(existente);
        return modelMapper.map(actualizado, BlogResponseDTO.class);
    }

    @Override
    public void borrar(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado"));
        blogRepository.delete(blog);
    }

    @Override
    public List<BlogResponseDTO> findAll() {
        List<Blog> lista = blogRepository.findAll();
        if (lista.isEmpty()) return List.of();
        return lista.stream().map(b -> modelMapper.map(b, BlogResponseDTO.class)).toList();
    }

    @Override
    public BlogResponseDTO findTipDelDia() {
        Blog blog = blogRepository.findFirstByBlogTipoOrderByBlogFechaPublicacionDesc(BlogTipo.TIP);
        if (blog == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay tip del día disponible");
        return modelMapper.map(blog, BlogResponseDTO.class);
    }

    @Override
    public List<BlogResponseDTO> findAllNews() {
        List<Blog> lista = blogRepository.findAllNews();
        if (lista.isEmpty()) return List.of();
        return lista.stream().map(b -> modelMapper.map(b, BlogResponseDTO.class)).toList();
    }

    @Override
    public BlogResponseDTO findById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog no encontrado"));
        return modelMapper.map(blog, BlogResponseDTO.class);
    }

    private void validarCampos(BlogRequestDTO dto) {
        if (dto.getBlogTitulo() == null || dto.getBlogTitulo().isBlank() ||
                dto.getBlogDescripcion() == null || dto.getBlogDescripcion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título y descripción son obligatorios");
        }
    }
}

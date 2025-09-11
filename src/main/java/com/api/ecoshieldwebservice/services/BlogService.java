package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.BlogResponseDTO;
import com.api.ecoshieldwebservice.entities.Blog;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.BlogTipo;
import com.api.ecoshieldwebservice.interfaces.IBlogServices;
import com.api.ecoshieldwebservice.repositories.BlogRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService implements IBlogServices {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BlogResponseDTO findById(Integer id) {
        return blogRepository.findById(id)
                .map(blog -> modelMapper.map(blog, BlogResponseDTO.class))
                .orElse(null);
    }

    @Override
    public BlogResponseDTO registrar(BlogRequestDTO blogRequestDTO) {
        Blog blog = modelMapper.map(blogRequestDTO, Blog.class);

        Usuario usuario = usuarioRepository.findById(blogRequestDTO.getUsuarioid())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        blog.setUsuarioid(usuario);
        blog.setBlogfechapublicacion(OffsetDateTime.now());

        Blog guardado = blogRepository.save(blog);
        return modelMapper.map(guardado, BlogResponseDTO.class);
    }

    @Override
    public BlogResponseDTO actualizar(Integer id, BlogRequestDTO blogRequestDTO) {
        if (blogRepository.existsById(id)) {
            Blog blog = modelMapper.map(blogRequestDTO, Blog.class);
            blog.setBlogid(id);

            Usuario usuario = usuarioRepository.findById(blogRequestDTO.getUsuarioid())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            blog.setUsuarioid(usuario);

            Blog actualizado = blogRepository.save(blog);
            return modelMapper.map(actualizado, BlogResponseDTO.class);
        }
        return null;
    }

    @Override
    public void borrar(Integer id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
        }
    }

    @Override
    public List<BlogResponseDTO> findAll() {
        return blogRepository.findAll()
                .stream()
                .map(blog -> modelMapper.map(blog, BlogResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BlogResponseDTO findTipDelDia() {
        Blog blog = blogRepository.findFirstByBlogtipoOrderByBlogfechapublicacionDesc(BlogTipo.TIP);
        return blog != null ? modelMapper.map(blog, BlogResponseDTO.class) : null;
    }

    @Override
    public List<BlogResponseDTO> findAllNews() {
        return blogRepository.findAllNews()
                .stream()
                .map(blog -> modelMapper.map(blog, BlogResponseDTO.class))
                .toList();
    }
}

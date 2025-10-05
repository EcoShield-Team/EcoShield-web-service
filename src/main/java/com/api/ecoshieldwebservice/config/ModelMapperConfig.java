package com.api.ecoshieldwebservice.config;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.FeedbackResponseDTO;
import com.api.ecoshieldwebservice.dtos.response.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Blog;
import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.entities.Feedback;
import com.api.ecoshieldwebservice.entities.Post;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(Feedback.class, FeedbackResponseDTO.class).addMappings(map -> {
            map.map(src -> src.getUsuario().getUsuarioId(), FeedbackResponseDTO::setUsuarioId);
            map.map(src -> src.getUsuario().getUsuarioNombre(), FeedbackResponseDTO::setUsuarioNombre);
        });

        mapper.typeMap(Comentario.class, ComentarioResponseDTO.class).addMappings(map -> {
            map.map(src -> src.getUsuario().getUsuarioId(), (dest, v) -> dest.getUsuario().setUsuarioId((Long) v));
            map.map(src -> src.getUsuario().getUsuarioNombre(), (dest, v) -> dest.getUsuario().setUsuarioNombre((String) v));
            map.map(src -> src.getUsuario().getUsuarioFotoPerfil(), (dest, v) -> dest.getUsuario().setUsuarioFotoPerfil((String) v));
            map.map(src -> src.getUsuario().getUsuarioPais(), (dest, v) -> dest.getUsuario().setUsuarioPais((String) v));
        });

        mapper.typeMap(Post.class, PostResponseDTO.class).addMappings(map -> {
            map.map(src -> src.getUsuario().getUsuarioId(), (dest, v) -> dest.getUsuario().setUsuarioId((Long) v));
            map.map(src -> src.getUsuario().getUsuarioNombre(), (dest, v) -> dest.getUsuario().setUsuarioNombre((String) v));
            map.map(src -> src.getUsuario().getUsuarioFotoPerfil(), (dest, v) -> dest.getUsuario().setUsuarioFotoPerfil((String) v));
            map.map(src -> src.getUsuario().getUsuarioPais(), (dest, v) -> dest.getUsuario().setUsuarioPais((String) v));
        });

        mapper.typeMap(Blog.class, BlogResponseDTO.class).addMappings(map -> {
            map.map(src -> src.getUsuario().getUsuarioId(), BlogResponseDTO::setUsuarioId);
        });

        mapper.typeMap(BlogRequestDTO.class, Blog.class)
                .addMappings(map -> map.skip(Blog::setBlogId));

        return mapper;
    }
}
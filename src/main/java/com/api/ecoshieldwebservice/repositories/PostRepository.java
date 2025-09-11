package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUsuarioid(Usuario usuario);
    List<Post> findByPosttitulo(String titulo);
}

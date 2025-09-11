package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUsuarioid_Id(Integer usuarioId);
    List<Post> findByPosttitulo(String titulo);
}

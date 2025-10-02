package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
    List<Comentario> findByPost_PostIdOrderByComentarioFechaAsc(Long postId);
    List<Comentario> findByUsuario(Usuario usuario);
}

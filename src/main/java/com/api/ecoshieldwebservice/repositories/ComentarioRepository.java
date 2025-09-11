package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
    List<Comentario> findByPostid_IdOrderByComentariofechaAsc(Integer postId);
    List<Comentario> findByUsuarioid_Id(Integer usuarioId);
}

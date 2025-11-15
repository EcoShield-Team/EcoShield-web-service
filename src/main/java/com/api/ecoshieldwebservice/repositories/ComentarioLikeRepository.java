package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.ComentarioLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioLikeRepository extends JpaRepository<ComentarioLike, Long> {
    boolean existsByComentario_ComentarioIdAndUsuario_UsuarioId(Long comentarioId, Long usuarioId);

    boolean existsByComentario_ComentarioIdAndUsuario_UsuarioCorreo(Long comentarioId, String usuarioCorreo);

    int countByComentario_ComentarioId(Long comentarioId);

    void deleteByComentario_ComentarioIdAndUsuario_UsuarioId(Long comentarioId, Long usuarioId);
}

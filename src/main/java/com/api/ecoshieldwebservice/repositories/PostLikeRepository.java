package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByPost_PostIdAndUsuario_UsuarioId(Long postId, Long usuarioId);

    int countByPost_PostId(Long postId);

    void deleteByPost_PostIdAndUsuario_UsuarioId(Long postId, Long usuarioId);

    boolean existsByPost_PostIdAndUsuario_UsuarioCorreo(Long postId, String correo);
}

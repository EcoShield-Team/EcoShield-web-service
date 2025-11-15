package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
    List<Comentario> findByPost_PostIdOrderByComentarioFechaDesc(Long postId);
    List<Comentario> findByUsuario(Usuario usuario);
    List<Comentario> findAllByOrderByComentarioFechaDesc();
    boolean existsByComentarioIdAndUsuario_UsuarioCorreo(Long id, String usuarioCorreo);
    int countByPost_PostId(Long postId);
}

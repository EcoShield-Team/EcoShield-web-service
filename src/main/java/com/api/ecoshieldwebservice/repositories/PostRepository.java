package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUsuario(Usuario usuario);
    List<Post> findByPostTitulo(String titulo);
    List<Post> findAllByOrderByPostFechaDesc();
    boolean existsByPostIdAndUsuario_UsuarioCorreo(Long id, String usuarioCorreo);
    @Query("SELECT p FROM Post p WHERE p.usuario.usuarioCorreo = :correo ORDER BY p.postFecha DESC")
    List<Post> listarPostsPorCorreo(String correo);
}

package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUsuario(Usuario usuario);
    List<Post> findAllByOrderByPostFechaDesc();
    boolean existsByPostIdAndUsuario_UsuarioCorreo(Long id, String usuarioCorreo);
    @Query("SELECT p FROM Post p WHERE p.usuario.usuarioCorreo = :correo ORDER BY p.postFecha DESC")
    List<Post> listarPostsPorCorreo(String correo);

    @Query("SELECT p FROM Post p " +
            "WHERE LOWER(p.postTitulo) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.postDescripcion) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "ORDER BY p.postFecha DESC")
    List<Post> buscarPostsRecientes(String query);

    @Query("SELECT p FROM Post p " +
            "LEFT JOIN p.likes l WHERE LOWER(p.postTitulo) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.postDescripcion) LIKE LOWER(CONCAT('%', :query, '%'))" +
            "GROUP BY p.postId ORDER BY COUNT(l) DESC")
    List<Post> buscarPostsDestacados(String query);
}

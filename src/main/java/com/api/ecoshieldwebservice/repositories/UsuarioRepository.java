package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsuarioCorreo(String usuariocorreo);
    boolean existsByUsuarioCorreo(String usuariocorreo);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.usuarioNombre) " +
            "LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Usuario> buscarUsuariosPorNombre(String query);

}

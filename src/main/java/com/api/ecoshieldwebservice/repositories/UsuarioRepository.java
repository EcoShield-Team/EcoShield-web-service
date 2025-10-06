package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.UsuarioEstado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsuarioCorreo(String usuariocorreo);
    boolean existsByUsuarioCorreo(String usuariocorreo);
}

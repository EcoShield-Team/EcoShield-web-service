package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Deteccion;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeteccionRepository extends JpaRepository<Deteccion, Long> {
    List<Deteccion> findByFoto_UsuarioOrderByDeteccionFechaDesc(Usuario usuario);
}

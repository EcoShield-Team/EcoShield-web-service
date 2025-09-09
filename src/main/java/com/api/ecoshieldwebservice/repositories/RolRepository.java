package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolname(String rolnombre);
}

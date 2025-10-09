package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Deteccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeteccionRepository extends JpaRepository<Deteccion, Long> {
}

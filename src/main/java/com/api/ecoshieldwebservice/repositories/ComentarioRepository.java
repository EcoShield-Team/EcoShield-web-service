package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario,Integer> {
}

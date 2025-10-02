package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Plaga;
import com.api.ecoshieldwebservice.enums.PlagaTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlagaRepository extends JpaRepository<Plaga, Long> {

    List<Plaga> findByPlagaNombreContainingIgnoreCase(String nombre);

    List<Plaga> findByPlagaTipo(PlagaTipo tipo);

    List<Plaga> findByTemporada(Temporada temporada);

    List<Plaga> findBySeveridad(Severidad severidad);

    @Query("SELECT p FROM Plaga p WHERE p.plagaTipo = :tipo AND p.plagaId <> :id")
    List<Plaga> findRelacionadas(PlagaTipo tipo, Long id);

    @Query ("""
           SELECT p FROM Plaga p
           ORDER BY 
             CASE p.severidad
               WHEN 'GRAVE' THEN 1
               WHEN 'MODERADA' THEN 2
               WHEN 'LEVE' THEN 3
               ELSE 4
             END
           """)
    List<Plaga> findAllOrderBySeveridad();
    List<Plaga> findAllByOrderByPlagaNombreAsc();
    List<Plaga> findAllByOrderByPlagaNombreDesc();
}
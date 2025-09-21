package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Plaga;
import com.api.ecoshieldwebservice.enums.PlagaTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlagaRepository extends JpaRepository<Plaga, Integer> {

    List<Plaga> findByPlaganombreContainingIgnoreCase(String nombre);

    List<Plaga> findByPlagatipo(PlagaTipo tipo);

    List<Plaga> findByTemporada(Temporada temporada);

    List<Plaga> findBySeveridad(Severidad severidad);

    @Query("SELECT p FROM Plaga p WHERE p.plagatipo = :tipo AND p.id <> :id")
    List<Plaga> findRelacionadas(PlagaTipo tipo, Integer id);

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
    List<Plaga> findAllByOrderByPlaganombreAsc();
    List<Plaga> findAllByOrderByPlaganombreDesc();
}
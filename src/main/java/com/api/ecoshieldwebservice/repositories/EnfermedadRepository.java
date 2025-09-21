package com.api.ecoshieldwebservice.repositories;

import com.api.ecoshieldwebservice.entities.Enfermedad;
import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnfermedadRepository extends JpaRepository<Enfermedad, Integer> {

    List<Enfermedad> findByEnfermedadnombreContainingIgnoreCase(String nombre);

    List<Enfermedad> findByEnfermedadtipo(EnfermedadTipo tipo);

    List<Enfermedad> findByTemporada(Temporada temporada);

    List<Enfermedad> findBySeveridad(Severidad severidad);

    @Query("SELECT e FROM Enfermedad e WHERE e.enfermedadtipo = :tipo AND e.id <> :id")
    List<Enfermedad> findRelacionadas(EnfermedadTipo tipo, Integer id);

    @Query ("""
           SELECT e FROM Enfermedad e
           ORDER BY 
             CASE e.severidad
               WHEN 'GRAVE' THEN 1
               WHEN 'MODERADA' THEN 2
               WHEN 'LEVE' THEN 3
               ELSE 4
             END
           """)
    List<Enfermedad> findAllOrderBySeveridad();
    List<Enfermedad> findAllByOrderByEnfermedadnombreAsc();
    List<Enfermedad> findAllByOrderByEnfermedadnombreDesc();
}
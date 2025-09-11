package com.api.ecoshieldwebservice.entities;

import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enfermedad")
public class Enfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enfermedadid", nullable = false)
    private Integer enfermedadid;

    @Size(max = 150)
    @NotNull
    @Column(name = "enfermedadnombre", nullable = false, length = 150)
    private String enfermedadnombre;

    @Size(max = 200)
    @NotNull
    @Column(name = "enfermedadnombrecientifico", nullable = false, length = 200)
    private String enfermedadnombrecientifico;

    @Enumerated(EnumType.STRING)
    @Column(name = "enfermedadtipo", nullable = false, length = 20)
    private EnfermedadTipo enfermedadtipo;

    @Column(name = "enfermedaddescripcion", length = Integer.MAX_VALUE)
    private String enfermedaddescripcion;

    @Column(name = "enfermedadsintomas", length = Integer.MAX_VALUE)
    private String enfermedadsintomas;

    @Column(name = "enfermedadtratamiento", length = Integer.MAX_VALUE)
    private String enfermedadtratamiento;

    @Column(name = "enfermedadcausas", length = Integer.MAX_VALUE)
    private String enfermedadcausas;

    @Column(name = "enfermedadprevenciones", length = Integer.MAX_VALUE)
    private String enfermedadprevenciones;

    @Size(max = 255)
    @NotNull
    @Column(name = "enfermedadfoto", nullable = false)
    private String enfermedadfoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "temporada", nullable = false, length = 20)
    private Temporada temporada;

    @Enumerated(EnumType.STRING)
    @Column(name = "severidad", nullable = false, length = 10)
    private Severidad severidad;
}
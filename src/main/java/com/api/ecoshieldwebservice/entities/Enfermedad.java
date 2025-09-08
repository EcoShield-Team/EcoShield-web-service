package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "enfermedadid", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "enfermedadnombre", nullable = false, length = 150)
    private String enfermedadnombre;

    @Size(max = 200)
    @NotNull
    @Column(name = "enfermedadnombrecientifico", nullable = false, length = 200)
    private String enfermedadnombrecientifico;

    @Size(max = 20)
    @NotNull
    @Column(name = "enfermedadtipo", nullable = false, length = 20)
    private String enfermedadtipo;

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

}
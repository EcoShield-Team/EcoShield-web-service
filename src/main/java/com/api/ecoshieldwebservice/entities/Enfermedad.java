package com.api.ecoshieldwebservice.entities;

import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import jakarta.persistence.*;
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
    private Long enfermedadId;

    @Column(name = "enfermedadnombre", nullable = false, length = 150)
    private String enfermedadNombre;

    @Column(name = "enfermedadnombrecientifico", nullable = false, length = 200)
    private String enfermedadNombreCientifico;

    @Enumerated(EnumType.STRING)
    @Column(name = "enfermedadtipo", nullable = false, length = 30)
    private EnfermedadTipo enfermedadTipo;

    @Lob
    @Column(name = "enfermedaddescripcion")
    private String enfermedadDescripcion;

    @Lob
    @Column(name = "enfermedadsintomas")
    private String enfermedadSintomas;

    @Lob
    @Column(name = "enfermedadtratamiento")
    private String enfermedadTratamiento;

    @Lob
    @Column(name = "enfermedadcausas")
    private String enfermedadCausas;

    @Lob
    @Column(name = "enfermedadprevenciones")
    private String enfermedadPrevenciones;

    @Column(name = "enfermedadfoto", nullable = false, length = 255)
    private String enfermedadFoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "temporada", nullable = false, length = 20)
    private Temporada temporada;

    @Enumerated(EnumType.STRING)
    @Column(name = "severidad", nullable = false, length = 15)
    private Severidad severidad;

}
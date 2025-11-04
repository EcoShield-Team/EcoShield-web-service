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

    @Column(name = "enfermedaddescripcion", columnDefinition = "text")
    private String enfermedadDescripcion;

    @Column(name = "enfermedadsintomas", columnDefinition = "text")
    private String enfermedadSintomas;

    @Column(name = "enfermedadtratamiento", columnDefinition = "text")
    private String enfermedadTratamiento;

    @Column(name = "enfermedadcausas", columnDefinition = "text")
    private String enfermedadCausas;

    @Column(name = "enfermedadprevenciones", columnDefinition = "text")
    private String enfermedadPrevenciones;

    @Column(name = "enfermedadfoto", nullable = false, length = 255)
    private String enfermedadFoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "temporada", nullable = false, length = 20)
    private Temporada temporada;

    @Enumerated(EnumType.STRING)
    @Column(name = "severidad", nullable = false, length = 15)
    private Severidad severidad;

    @ElementCollection
    @CollectionTable(name = "enfermedad_alias", joinColumns = @JoinColumn(name = "enfermedad_id"))
    @Column(name = "alias", length = 150)
    private java.util.List<String> alias;

}
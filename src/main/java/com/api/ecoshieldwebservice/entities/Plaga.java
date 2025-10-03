package com.api.ecoshieldwebservice.entities;

import com.api.ecoshieldwebservice.enums.PlagaTipo;
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
@Table(name = "plaga")
public class Plaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plagaid", nullable = false)
    private Long plagaId;

    @Column(name = "plaganombre", nullable = false, length = 150)
    private String plagaNombre;

    @Column(name = "plaganombrecientifico", length = 200)
    private String plagaNombreCientifico;

    @Enumerated(EnumType.STRING)
    @Column(name = "plagatipo", length = 30)
    private PlagaTipo plagaTipo;

    @Column(name = "plagadescripcion", columnDefinition = "text")
    private String plagaDescripcion;

    @Column(name = "plagasintomas", columnDefinition = "text")
    private String plagaSintomas;

    @Column(name = "plagatratamiento", columnDefinition = "text")
    private String plagaTratamiento;

    @Column(name = "plagacausas", columnDefinition = "text")
    private String plagaCausas;

    @Column(name = "plagaprevenciones", columnDefinition = "text")
    private String plagaPrevenciones;

    @Column(name = "plagafoto", length = 255)
    private String plagaFoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "temporada", nullable = false, length = 20)
    private Temporada temporada;

    @Enumerated(EnumType.STRING)
    @Column(name = "severidad", nullable = false, length = 15)
    private Severidad severidad;

}
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

    @Lob
    @Column(name = "plagadescripcion")
    private String plagaDescripcion;

    @Lob
    @Column(name = "plagasintomas")
    private String plagaSintomas;

    @Lob
    @Column(name = "plagatratamiento")
    private String plagaTratamiento;

    @Lob
    @Column(name = "plagacausas")
    private String plagaCausas;

    @Lob
    @Column(name = "plagaprevenciones")
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
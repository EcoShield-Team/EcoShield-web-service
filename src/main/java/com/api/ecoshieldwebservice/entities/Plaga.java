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
    private Integer plagaid;

    @Size(max = 150)
    @NotNull
    @Column(name = "plaganombre", nullable = false, length = 150)
    private String plaganombre;

    @Size(max = 200)
    @Column(name = "plaganombrecientifico", length = 200)
    private String plaganombrecientifico;

    @Enumerated(EnumType.STRING)
    @Column(name = "plagatipo", length = 20)
    private PlagaTipo plagatipo;

    @Column(name = "plagadescripcion", length = Integer.MAX_VALUE)
    private String plagadescripcion;

    @Column(name = "plagasintomas", length = Integer.MAX_VALUE)
    private String plagasintomas;

    @Column(name = "plagatratamiento", length = Integer.MAX_VALUE)
    private String plagatratamiento;

    @Column(name = "plagacausas", length = Integer.MAX_VALUE)
    private String plagacausas;

    @Column(name = "plagaprevenciones", length = Integer.MAX_VALUE)
    private String plagaprevenciones;

    @Size(max = 255)
    @Column(name = "plagafoto")
    private String plagafoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "temporada", nullable = false, length = 20)
    private Temporada temporada;

    @Enumerated(EnumType.STRING)
    @Column(name = "severidad", nullable = false, length = 10)
    private Severidad severidad;
}
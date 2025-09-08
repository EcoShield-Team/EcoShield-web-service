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
@Table(name = "plaga")
public class Plaga {
    @Id
    @Column(name = "plagaid", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "plaganombre", nullable = false, length = 150)
    private String plaganombre;

    @Size(max = 200)
    @Column(name = "plaganombrecientifico", length = 200)
    private String plaganombrecientifico;

    @Size(max = 20)
    @Column(name = "plagatipo", length = 20)
    private String plagatipo;

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

}
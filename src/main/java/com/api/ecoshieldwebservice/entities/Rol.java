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
@Table(name = "rol")
public class Rol {
    @Id
    @Column(name = "rolid", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "rolnombre", nullable = false, length = 50)
    private String rolnombre;

    @Size(max = 100)
    @Column(name = "roldescripcion", length = 100)
    private String roldescripcion;

}
package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolid", nullable = false)
    private Integer rolid;

    @Size(max = 50)
    @NotNull
    @Column(name = "rolnombre", nullable = false, length = 50)
    private String rolnombre;

    @Size(max = 100)
    @Column(name = "roldescripcion", length = 100)
    private String roldescripcion;

}
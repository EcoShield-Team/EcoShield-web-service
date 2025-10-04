package com.api.ecoshieldwebservice.entities;

import com.api.ecoshieldwebservice.enums.RolNombre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolid", nullable = false)
    private Long rolId;

    @Enumerated(EnumType.STRING)
    @Column(name = "rolnombre", nullable = false, length = 50)
    private RolNombre rolNombre;

    @Column(name = "roldescripcion", length = 100)
    private String rolDescripcion;

}
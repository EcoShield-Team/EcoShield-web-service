package com.api.ecoshieldwebservice.entities;

import com.api.ecoshieldwebservice.enums.RolNombre;
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
    private Long rolId;

    @Column(name = "rolnombre", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    @Column(name = "roldescripcion", length = 100)
    private String rolDescripcion;

}
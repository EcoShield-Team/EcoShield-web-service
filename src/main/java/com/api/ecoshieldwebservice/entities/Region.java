package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regionid", nullable = false)
    private Long regionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deteccionid", nullable = false)
    private Deteccion deteccion;

    @Column(name = "regionx", nullable = false, precision = 10, scale = 4)
    private BigDecimal regionX;

    @Column(name = "regiony", nullable = false, precision = 10, scale = 4)
    private BigDecimal regionY;

    @Column(name = "regionancho", nullable = false, precision = 10, scale = 4)
    private BigDecimal regionAncho;

    @Column(name = "regionalto", nullable = false, precision = 10, scale = 4)
    private BigDecimal regionAlto;

}
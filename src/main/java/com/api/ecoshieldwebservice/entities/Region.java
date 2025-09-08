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
    @Column(name = "regionid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deteccionid", nullable = false)
    private Deteccion deteccionid;

    @NotNull
    @Column(name = "regionx", nullable = false, precision = 6, scale = 4)
    private BigDecimal regionx;

    @NotNull
    @Column(name = "regiony", nullable = false, precision = 6, scale = 4)
    private BigDecimal regiony;

    @NotNull
    @Column(name = "regionancho", nullable = false, precision = 6, scale = 4)
    private BigDecimal regionancho;

    @NotNull
    @Column(name = "regionalto", nullable = false, precision = 6, scale = 4)
    private BigDecimal regionalto;

}
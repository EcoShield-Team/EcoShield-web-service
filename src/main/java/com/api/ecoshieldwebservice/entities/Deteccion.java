package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "deteccion")
public class Deteccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deteccionid", nullable = false)
    private Long deteccionId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fotoid", nullable = false)
    private Foto foto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plagaid")
    private Plaga plaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfermedadid")
    private Enfermedad enfermedad;

    @Column(name = "deteccionresultado", nullable = false, columnDefinition = "text")
    private String deteccionResultado;

    @Column(name = "deteccionconfianza")
    private Double confianza;

    @Column(precision = 10, scale = 4)
    private BigDecimal regionX;

    @Column(precision = 10, scale = 4)
    private BigDecimal regionY;

    @Column(precision = 10, scale = 4)
    private BigDecimal regionAncho;

    @Column(precision = 10, scale = 4)
    private BigDecimal regionAlto;

    @CreationTimestamp
    @Column(name = "deteccionfecha", nullable = false, updatable = false)
    private OffsetDateTime deteccionFecha;

}
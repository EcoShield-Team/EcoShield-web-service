package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fotoid", nullable = false)
    private Foto foto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plagaid")
    private Plaga plaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfermedadid")
    private Enfermedad enfermedad;

    @Lob
    @Column(name = "deteccionresultado", nullable = false)
    private String deteccionResultado;

    @CreationTimestamp
    @Column(name = "deteccionfecha", nullable = false, updatable = false)
    private OffsetDateTime deteccionFecha;

}
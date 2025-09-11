package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "deteccion")
public class Deteccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deteccionid", nullable = false)
    private Integer deteccionid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fotoid", nullable = false)
    private Foto fotoid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plagaid")
    private Plaga plagaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfermedadid")
    private Enfermedad enfermedadid;

    @NotNull
    @Column(name = "deteccionresultado", nullable = false, length = Integer.MAX_VALUE)
    private String deteccionresultado;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "deteccionfecha", nullable = false)
    private OffsetDateTime deteccionfecha;

}
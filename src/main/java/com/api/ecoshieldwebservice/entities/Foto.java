package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "foto")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fotoid", nullable = false)
    private Long fotoId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @Column(name = "fotoruta", nullable = false)
    private String fotoRuta;

    @CreationTimestamp
    @Column(name = "fotofechasubida", nullable = false, updatable = false)
    private OffsetDateTime fotoFechaSubida;

    @OneToOne(mappedBy = "foto", cascade = CascadeType.ALL)
    private Deteccion deteccion;

}
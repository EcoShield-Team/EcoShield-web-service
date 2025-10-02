package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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

    @Column(name = "fotoruta", nullable = false, length = 255)
    private String fotoRuta;

    @CreationTimestamp
    @Column(name = "fotofechasubida", nullable = false, updatable = false)
    private OffsetDateTime fotoFechaSubida;

    @Column(name = "fotoformato", length = 20)
    private String fotoFormato;

    @Column(name = "fotoestado", nullable = false, length = 20)
    private String fotoEstado;

    @Column(name = "fotoerrormensaje", length = 255)
    private String fotoErrorMensaje;

}
package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "foto")
public class Foto {
    @Id
    @Column(name = "fotoid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuarioid;

    @Size(max = 255)
    @NotNull
    @Column(name = "fotoruta", nullable = false)
    private String fotoruta;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "fotofechasubida", nullable = false)
    private OffsetDateTime fotofechasubida;

    @Size(max = 20)
    @Column(name = "fotoformato", length = 20)
    private String fotoformato;

    @Size(max = 20)
    @NotNull
    @Column(name = "fotoestado", nullable = false, length = 20)
    private String fotoestado;

    @Size(max = 255)
    @Column(name = "fotoerrormensaje")
    private String fotoerrormensaje;

}
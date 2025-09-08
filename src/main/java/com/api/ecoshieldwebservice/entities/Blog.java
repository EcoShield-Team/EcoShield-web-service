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
@Table(name = "blog")
public class Blog {
    @Id
    @Column(name = "blogid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuarioid;

    @Size(max = 20)
    @NotNull
    @Column(name = "blogtipo", nullable = false, length = 20)
    private String blogtipo;

    @Size(max = 200)
    @NotNull
    @Column(name = "blogtitulo", nullable = false, length = 200)
    private String blogtitulo;

    @NotNull
    @Column(name = "blogdescripcion", nullable = false, length = Integer.MAX_VALUE)
    private String blogdescripcion;

    @Size(max = 255)
    @NotNull
    @Column(name = "blogimagen", nullable = false)
    private String blogimagen;

    @NotNull
    @Column(name = "blogdestacado", nullable = false)
    private Boolean blogdestacado = false;

    @Size(max = 20)
    @NotNull
    @Column(name = "blogestado", nullable = false, length = 20)
    private String blogestado;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "blogfechapublicacion", nullable = false)
    private OffsetDateTime blogfechapublicacion;

}
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
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "postid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuarioid;

    @Size(max = 200)
    @NotNull
    @Column(name = "posttitulo", nullable = false, length = 200)
    private String posttitulo;

    @NotNull
    @Column(name = "postdescripcion", nullable = false, length = Integer.MAX_VALUE)
    private String postdescripcion;

    @Size(max = 255)
    @NotNull
    @Column(name = "postfoto", nullable = false)
    private String postfoto;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "postfecha", nullable = false)
    private OffsetDateTime postfecha;

}
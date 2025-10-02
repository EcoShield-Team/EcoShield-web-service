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
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid", nullable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @Column(name = "posttitulo", nullable = false, length = 200)
    private String postTitulo;

    @Lob
    @Column(name = "postdescripcion", nullable = false)
    private String postDescripcion;

    @Column(name = "postfoto", length = 255)
    private String postFoto;

    @CreationTimestamp
    @Column(name = "postfecha", nullable = false, updatable = false)
    private OffsetDateTime postFecha;
}
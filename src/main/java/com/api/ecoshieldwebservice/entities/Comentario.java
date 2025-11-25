package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentarioid", nullable = false)
    private Long comentarioId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postid", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @Column(name = "comentariotexto", nullable = false, columnDefinition = "text")
    private String comentarioTexto;

    @CreationTimestamp
    @Column(name = "comentariofecha", nullable = false, updatable = false)
    private OffsetDateTime comentarioFecha;

    @UpdateTimestamp
    @Column(name = "comentariofechamodificacion")
    private OffsetDateTime comentarioFechaModificacion;

    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioLike> likes = new ArrayList<>();

}
package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "comentario_like", uniqueConstraints = @UniqueConstraint(columnNames = {"comentarioid", "usuarioid"}))
public class ComentarioLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeid", nullable = false)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comentarioid", nullable = false)
    private Comentario comentario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @CreationTimestamp
    @Column(name = "fechalike", nullable = false)
    private OffsetDateTime fechaLike;
}

package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "post_like", uniqueConstraints = @UniqueConstraint(columnNames = {"postid", "usuarioid"}))
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeid", nullable = false)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postid", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @CreationTimestamp
    @Column(name = "fechalike", nullable = false)
    private OffsetDateTime fechaLike;
}
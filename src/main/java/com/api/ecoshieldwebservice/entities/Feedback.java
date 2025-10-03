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
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbackid", nullable = false)
    private Long feedbackId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @Column(name = "feedbacktipo", nullable = false, length = 50)
    private String feedbackTipo;

    @Column(name = "feedbackdescripcion", nullable = false, columnDefinition = "text")
    private String feedbackDescripcion;

    @Column(name = "feedbackrating")
    private Integer feedbackRating;

    @CreationTimestamp
    @Column(name = "feedbackfecha", nullable = false, updatable = false)
    private OffsetDateTime feedbackFecha;

}
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
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbackid", nullable = false)
    private Integer feedbackid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuarioid;

    @Size(max = 50)
    @NotNull
    @Column(name = "feedbacktipo", nullable = false, length = 50)
    private String feedbacktipo;

    @NotNull
    @Column(name = "feedbackdescripcion", nullable = false, length = Integer.MAX_VALUE)
    private String feedbackdescripcion;

    @Column(name = "feedbackrating")
    private Integer feedbackrating;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "feedbackfecha", nullable = false)
    private OffsetDateTime feedbackfecha;
}
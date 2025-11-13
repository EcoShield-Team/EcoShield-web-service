package com.api.ecoshieldwebservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "password")
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=100)
    private String token;

    @Column(nullable = false, length = 6)
    private String verificationCode;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="usuarioid", nullable=false)
    private Usuario usuario;

    @Column(nullable=false)
    private OffsetDateTime createdAt;

    @Column(nullable=false)
    private OffsetDateTime expiresAt;

    @Column
    private OffsetDateTime usedAt;
}

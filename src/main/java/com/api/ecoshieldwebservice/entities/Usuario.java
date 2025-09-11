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
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioid", nullable = false)
    private Integer usuarioid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rolid", nullable = false)
    private Rol rolid;

    @Size(max = 100)
    @NotNull
    @Column(name = "usuarionombre", nullable = false, length = 100)
    private String usuarionombre;

    @Size(max = 150)
    @NotNull
    @Column(name = "usuariocorreo", nullable = false, length = 150)
    private String usuariocorreo;

    @Size(max = 255)
    @NotNull
    @Column(name = "usuariocontrasena", nullable = false)
    private String usuariocontrasena;

    @Size(max = 20)
    @NotNull
    @Column(name = "usuarioestado", nullable = false, length = 20)
    private String usuarioestado;

    @Size(max = 500)
    @Column(name = "usuariofotoperfil", length = 500)
    private String usuariofotoperfil;

    @Size(max = 100)
    @NotNull
    @Column(name = "usuariopais", nullable = false, length = 100)
    private String usuariopais;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "usuariofecharegistro", nullable = false)
    private OffsetDateTime usuariofecharegistro;

}
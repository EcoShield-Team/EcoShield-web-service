package com.api.ecoshieldwebservice.entities;

import com.api.ecoshieldwebservice.enums.UsuarioEstado;
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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioid", nullable = false)
    private Long usuarioId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rolid", nullable = false)
    private Rol rol;

    @Column(name = "usuarionombre", nullable = false, length = 100)
    private String usuarioNombre;

    @Column(name = "usuariocorreo", nullable = false, length = 150)
    private String usuarioCorreo;

    @Column(name = "usuariocontrasena", nullable = false, length = 255)
    private String usuarioContrasena;

    @Column(name = "usuarioestado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UsuarioEstado usuarioEstado;

    @Column(name = "usuariofotoperfil", length = 500)
    private String usuarioFotoPerfil;

    @Column(name = "usuariopais", nullable = false, length = 100)
    private String usuarioPais;

    @CreationTimestamp
    @Column(name = "usuariofecharegistro", nullable = false, updatable = false)
    private OffsetDateTime usuarioFechaRegistro;

}
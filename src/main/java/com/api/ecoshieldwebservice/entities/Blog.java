package com.api.ecoshieldwebservice.entities;

import com.api.ecoshieldwebservice.enums.BlogEstado;
import com.api.ecoshieldwebservice.enums.BlogTipo;
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
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogid", nullable = false)
    private Long blogId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;  // mejor nombre que "usuarioid"

    @Enumerated(EnumType.STRING)
    @Column(name = "blogtipo", nullable = false, length = 20)
    private BlogTipo blogTipo;

    @Column(name = "blogtitulo", nullable = false, length = 200)
    private String blogTitulo;

    @Column(name = "blogdescripcion", nullable = false, columnDefinition = "text")
    private String blogDescripcion;

    @Column(name = "blogimagen", length = 255)
    private String blogImagen;

    @Enumerated(EnumType.STRING)
    @Column(name = "blogestado", nullable = false, length = 20)
    private BlogEstado blogEstado;

    // Timestamp automático de creación
    @CreationTimestamp
    @Column(name = "blogfechapublicacion", nullable = false, updatable = false)
    private OffsetDateTime blogFechaPublicacion;

}
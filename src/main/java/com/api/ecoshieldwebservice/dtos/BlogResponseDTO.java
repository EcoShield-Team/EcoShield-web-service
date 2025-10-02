package com.api.ecoshieldwebservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponseDTO {

    private Long blogId;
    private Long usuarioId;
    private String blogTipo;
    private String blogTitulo;
    private String blogDescripcion;
    private String blogImagen;
    private String blogEstado;
    private OffsetDateTime blogFechaPublicacion;

}

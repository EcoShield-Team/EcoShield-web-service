package com.api.ecoshieldwebservice.dtos.response;

import com.api.ecoshieldwebservice.enums.BlogEstado;
import com.api.ecoshieldwebservice.enums.BlogTipo;
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
    private BlogTipo blogTipo;
    private String blogTitulo;
    private String blogDescripcion;
    private String blogImagen;
    private BlogEstado blogEstado;
    private OffsetDateTime blogFechaPublicacion;

}

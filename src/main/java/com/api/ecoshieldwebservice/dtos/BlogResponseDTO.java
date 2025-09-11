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
    private Integer blogid;
    private Integer usuarioid;
    private String blogtipo;
    private String blogtitulo;
    private String blogdescripcion;
    private String blogimagen;
    private Boolean blogdestacado;
    private String blogestado;
    private OffsetDateTime blogfechapublicacion;
}

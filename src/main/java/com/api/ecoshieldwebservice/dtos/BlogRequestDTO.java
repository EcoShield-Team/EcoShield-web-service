package com.api.ecoshieldwebservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequestDTO {
    private Integer usuarioid;
    private String blogtipo;
    private String blogtitulo;
    private String blogdescripcion;
    private String blogimagen;
    private String blogestado;
}

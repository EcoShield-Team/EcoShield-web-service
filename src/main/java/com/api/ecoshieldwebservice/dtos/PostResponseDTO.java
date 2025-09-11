package com.api.ecoshieldwebservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PostResponseDTO {
    private Integer postid;
    private UsuarioUpdateDTO usuario;
    private String posttitulo;
    private String postdescripcion;
    private String postfoto;
    private OffsetDateTime postfecha;
}

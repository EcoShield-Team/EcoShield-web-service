package com.api.ecoshieldwebservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PostResponseDTO {

    private Long postId;
    private UsuarioResponseForoDTO usuario;
    private String postTitulo;
    private String postDescripcion;
    private String postFoto;
    private OffsetDateTime postFecha;

}

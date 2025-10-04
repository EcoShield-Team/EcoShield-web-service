package com.api.ecoshieldwebservice.dtos.response;

import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseForoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ComentarioResponseDTO {
    private Long comentarioId;
    private UsuarioResponseForoDTO usuario;
    private String comentarioTexto;
    private OffsetDateTime comentarioFecha;
}

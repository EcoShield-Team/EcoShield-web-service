package com.api.ecoshieldwebservice.dtos.response;

import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseForoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ComentarioResponseDTO {
    private Long comentarioId;
    private Long postId;
    private String comentarioTexto;
    private OffsetDateTime comentarioFecha;
    private OffsetDateTime comentarioFechaModificacion;
    private UsuarioResponseForoDTO usuario;
    private int likeCount;
    private boolean userLiked;
    private boolean editado;
}

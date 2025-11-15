package com.api.ecoshieldwebservice.dtos.response;

import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseForoDTO;
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
    private int likeCount;
    private boolean userLiked;
    private int commentCount;
}

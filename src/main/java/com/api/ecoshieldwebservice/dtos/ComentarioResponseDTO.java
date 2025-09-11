package com.api.ecoshieldwebservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ComentarioResponseDTO {
    private Integer id;
    private Integer postid;
    private Integer usuarioid;
    private String comentariotexto;
    private OffsetDateTime comentariofecha;
}

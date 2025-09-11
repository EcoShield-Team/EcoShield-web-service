package com.api.ecoshieldwebservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioRequestDTO {
    private Integer postid;
    private Integer usuarioid;
    private String comentariotexto;
}

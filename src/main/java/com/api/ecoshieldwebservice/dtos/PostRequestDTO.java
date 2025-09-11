package com.api.ecoshieldwebservice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private Integer usuarioid;
    private String posttitulo;
    private String postdescripcion;
    private String postfoto;
}

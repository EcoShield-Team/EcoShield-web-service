package com.api.ecoshieldwebservice.dtos;

import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnfermedadListDTO {
    private Integer enfermedadid;
    private String enfermedadnombre;
    private EnfermedadTipo enfermedadtipo;
    private String enfermedadfoto;
    private Temporada temporada;
    private Severidad severidad;
}
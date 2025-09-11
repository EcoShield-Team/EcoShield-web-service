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
public class EnfermedadDetailDTO {
    private Integer id;
    private String enfermedadnombre;
    private String enfermedadnombrecientifico;
    private EnfermedadTipo enfermedadtipo;
    private String enfermedaddescripcion;
    private String enfermedadsintomas;
    private String enfermedadtratamiento;
    private String enfermedadcausas;
    private String enfermedadprevenciones;
    private String enfermedadfoto;
    private Temporada temporada;
    private Severidad severidad;
}
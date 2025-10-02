package com.api.ecoshieldwebservice.dtos;

import com.api.ecoshieldwebservice.enums.PlagaTipo;
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
public class PlagaDetailDTO {

    private Long plagaId;
    private String plagaNombre;
    private String plagaNombreCientifico;
    private PlagaTipo plagaTipo;
    private String plagaDescripcion;
    private String plagaSintomas;
    private String plagaTratamiento;
    private String plagaCausas;
    private String plagaPrevenciones;
    private String plagaFoto;
    private Temporada temporada;
    private Severidad severidad;

}
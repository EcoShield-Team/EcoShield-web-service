package com.api.ecoshieldwebservice.dtos.almanaque;

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
    private Long enfermedadId;
    private String enfermedadNombre;
    private String enfermedadNombreCientifico;
    private EnfermedadTipo enfermedadTipo;

    private String enfermedadDescripcion;
    private String enfermedadSintomas;
    private String enfermedadTratamiento;
    private String enfermedadCausas;
    private String enfermedadPrevenciones;

    private String enfermedadFoto;
    private Temporada temporada;
    private Severidad severidad;
}
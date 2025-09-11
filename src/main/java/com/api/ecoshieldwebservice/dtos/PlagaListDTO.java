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
public class PlagaListDTO {
    private Integer plagaid;
    private String plaganombre;
    private PlagaTipo plagatipo;
    private String plagafoto;
    private Temporada temporada;
    private Severidad severidad;
}
package com.api.ecoshieldwebservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDTO {
    private Integer usuarioid;
    private String usuarionombre;
    private String usuariofotoperfil;
    private String usuariopais;
}

package com.api.ecoshieldwebservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioProfileDTO {
    private String usuarionombre;
    private String usuariofotoperfil;
    private String usuariopais;
    private OffsetDateTime usuariofecharegistro;
}

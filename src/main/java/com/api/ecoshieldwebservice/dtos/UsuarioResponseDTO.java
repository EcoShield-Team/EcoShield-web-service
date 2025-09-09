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
public class UsuarioResponseDTO {
    private Integer usuarioid;
    private String usuarionombre;
    private String usuariocorreo;
    private String usuariopais;
    private String usuariofotoperfil;
    private String usuarioestado;
    private OffsetDateTime usuariofecharegistro;
}

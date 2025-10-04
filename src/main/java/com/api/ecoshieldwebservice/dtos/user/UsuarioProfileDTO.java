package com.api.ecoshieldwebservice.dtos.user;

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
    private String usuarioNombre;
    private String usuarioFotoPerfil;
    private String usuarioPais;
    private OffsetDateTime usuarioFechaRegistro;
}

package com.api.ecoshieldwebservice.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAuthResponseDTO {
    private Long usuarioId;
    private String usuarioNombre;
    private String usuarioCorreo;
    private String usuarioRol;
}
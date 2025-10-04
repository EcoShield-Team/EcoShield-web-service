package com.api.ecoshieldwebservice.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseForoDTO {

    private Long usuarioId;
    private String usuarioNombre;
    private String usuarioFotoPerfil;
    private String usuarioPais;

}

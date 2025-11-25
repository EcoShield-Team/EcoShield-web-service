package com.api.ecoshieldwebservice.dtos.user;

import com.api.ecoshieldwebservice.enums.RolNombre;
import com.api.ecoshieldwebservice.enums.UsuarioEstado;
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

    private Long usuarioId;
    private String usuarioNombre;
    private String usuarioCorreo;
    private String usuarioPais;
    private String usuarioFotoPerfil;
    private UsuarioEstado usuarioEstado;
    private RolNombre rolNombre;
    private OffsetDateTime usuarioFechaRegistro;
    private boolean online;
}

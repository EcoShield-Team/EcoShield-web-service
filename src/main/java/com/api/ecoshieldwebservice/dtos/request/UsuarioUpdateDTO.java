package com.api.ecoshieldwebservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar los 100 caracteres")
    private String usuarioNombre;

    @Size(max = 500, message = "La URL de la foto no debe superar los 500 caracteres")
    private String usuarioFotoPerfil;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 100, message = "El país no debe superar los 100 caracteres")
    private String usuarioPais;

}

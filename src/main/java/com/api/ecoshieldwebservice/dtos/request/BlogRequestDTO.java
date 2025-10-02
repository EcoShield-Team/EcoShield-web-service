package com.api.ecoshieldwebservice.dtos.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequestDTO {

    @NotNull(message = "Usuario obligatorio")
    @Positive(message = "UsuarioId debe ser positivo")
    private Long usuarioId;

    @NotBlank(message = "Tipo obligatorio")
    @Pattern(
            regexp = "TIP|NEWS",
            message = "Tipo inválido"
    )
    private String blogTipo;

    @NotBlank(message = "Título obligatorio")
    @Size(min = 3, max = 200, message = "Título entre 3 y 200 caracteres")
    private String blogTitulo;

    @NotBlank(message = "Descripción obligatoria")
    @Size(min = 10, max = 4000, message = "Descripción entre 10 y 4000 caracteres")
    private String blogDescripcion;

    @Size(max = 255, message = "Imagen: máximo 255 caracteres")
    @URL(message = "URL de imagen inválida")
    private String blogImagen;

    @NotBlank(message = "Estado obligatorio")
    @Pattern(
            regexp = "ACTIVO|INACTIVO|ARCHIVADO",
            message = "Estado inválido"
    )
    private String blogEstado;
}

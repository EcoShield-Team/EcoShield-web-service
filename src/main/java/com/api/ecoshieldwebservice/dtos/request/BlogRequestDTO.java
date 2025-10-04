package com.api.ecoshieldwebservice.dtos.request;

import com.api.ecoshieldwebservice.enums.BlogEstado;
import com.api.ecoshieldwebservice.enums.BlogTipo;
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

    @NotNull(message = "Tipo obligatorio")
    private BlogTipo blogTipo;

    @NotBlank(message = "Título obligatorio")
    @Size(min = 3, max = 200, message = "Título entre 3 y 200 caracteres")
    private String blogTitulo;

    @NotBlank(message = "Descripción obligatoria")
    @Size(min = 10, max = 4000, message = "Descripción entre 10 y 4000 caracteres")
    private String blogDescripcion;

    @Size(max = 255, message = "Imagen: máximo 255 caracteres")
    @URL(message = "URL de imagen inválida")
    private String blogImagen;

    @NotNull(message = "Estado obligatorio")
    private BlogEstado blogEstado;
}

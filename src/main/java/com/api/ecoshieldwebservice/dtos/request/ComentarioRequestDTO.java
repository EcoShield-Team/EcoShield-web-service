package com.api.ecoshieldwebservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioRequestDTO {
    @NotNull(message = "El post es obligatorio")
    @Positive(message = "El id del post debe ser positivo")
    private Long postId;

    @NotNull(message = "El usuario es obligatorio")
    @Positive(message = "El id del usuario debe ser positivo")
    private Long usuarioId;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(min = 2, max = 1000, message = "El comentario debe tener entre 2 y 1000 caracteres")
    private String comentarioTexto;
}

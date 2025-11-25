package com.api.ecoshieldwebservice.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(min = 2, max = 1000, message = "El comentario debe tener entre 2 y 1000 caracteres")
    private String comentarioTexto;
}

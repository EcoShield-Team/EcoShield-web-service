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
public class PostRequestDTO {
    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 200, message = "El título debe tener entre 3 y 200 caracteres")
    private String postTitulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 2000, message = "La descripción debe tener entre 5 y 2000 caracteres")
    private String postDescripcion;

}

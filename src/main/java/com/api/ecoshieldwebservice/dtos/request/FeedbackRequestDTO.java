package com.api.ecoshieldwebservice.dtos.request;

import com.api.ecoshieldwebservice.enums.FeedbackTipo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequestDTO {
    @NotNull(message = "El tipo de feedback es obligatorio")
    private FeedbackTipo feedbackTipo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 300, message = "La descripción debe tener entre 5 y 300 caracteres")
    private String feedbackDescripcion;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer feedbackRating;

}
package com.api.ecoshieldwebservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDTO {
    private Long feedbackId;
    private Long usuarioId;
    private String usuarioNombre;
    private String feedbackTipo;
    private String feedbackDescripcion;
    private Integer feedbackRating;
    private OffsetDateTime feedbackFecha;
}

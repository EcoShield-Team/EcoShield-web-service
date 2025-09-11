package com.api.ecoshieldwebservice.dtos;

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
    private Integer id;
    private Integer usuarioid;
    private String feedbacktipo;
    private String feedbackdescripcion;
    private Integer feedbackrating;
    private OffsetDateTime feedbackfecha;
}

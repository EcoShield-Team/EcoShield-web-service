package com.api.ecoshieldwebservice.dtos;

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
    @NotNull
    private Integer usuarioid;

    @NotBlank
    @Size(max = 50)
    private String feedbacktipo;

    @NotBlank
    @Size(max = 500)
    private String feedbackdescripcion;

    @Min(1) @Max(5)
    private Integer feedbackrating;

}

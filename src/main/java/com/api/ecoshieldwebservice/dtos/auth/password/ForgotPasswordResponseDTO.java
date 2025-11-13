package com.api.ecoshieldwebservice.dtos.auth.password;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForgotPasswordResponseDTO {
    private String message;
    private String tokenPreview;
    private String verificationCode;
}

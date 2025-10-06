package com.api.ecoshieldwebservice.dtos.auth.password;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateTokenResponseDTO {
    private boolean valid;
    private String message;
}

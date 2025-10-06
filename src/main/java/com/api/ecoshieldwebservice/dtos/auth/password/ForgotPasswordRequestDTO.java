package com.api.ecoshieldwebservice.dtos.auth.password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequestDTO {
    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "Debe ingresar un correo válido.")
    private String email;
}

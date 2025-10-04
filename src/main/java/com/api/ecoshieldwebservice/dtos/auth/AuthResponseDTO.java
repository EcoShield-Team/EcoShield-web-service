package com.api.ecoshieldwebservice.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    String token;
    Instant expiresAt;
    UsuarioAuthResponseDTO usuario;
}
package com.api.ecoshieldwebservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDTO {
    private String usuariocorreo;
    private String token;
    private String actualcontrasena;
    private String nuevacontrasena;
}

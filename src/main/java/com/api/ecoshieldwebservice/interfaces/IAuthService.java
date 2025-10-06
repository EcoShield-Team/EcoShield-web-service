package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.auth.*;
import com.api.ecoshieldwebservice.dtos.auth.password.ChangePasswordRequestDTO;
import com.api.ecoshieldwebservice.dtos.auth.password.ResetPasswordRequestDTO;

public interface IAuthService {
    AuthResponseDTO login(LoginRequestDTO request);
    AuthResponseDTO register(RegisterRequestDTO dto);
    AuthResponseDTO changeMyPassword(String correo, ChangePasswordRequestDTO dto);
}
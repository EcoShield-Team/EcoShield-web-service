package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.auth.*;

public interface IAuthServices {
    AuthResponseDTO login(LoginRequestDTO request);
    AuthResponseDTO register(RegisterRequestDTO dto);
    AuthResponseDTO changeMyPassword(String correo, ChangePasswordRequestDTO dto);
    void adminResetPassword(Long usuarioId, ResetPasswordRequestDTO dto);
}
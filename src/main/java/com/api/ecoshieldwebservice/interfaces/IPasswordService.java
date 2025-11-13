package com.api.ecoshieldwebservice.interfaces;


import com.api.ecoshieldwebservice.dtos.auth.password.*;

public interface IPasswordService {
    ForgotPasswordResponseDTO requestReset(ForgotPasswordRequestDTO req);
    ValidateTokenResponseDTO validateToken(String token);
    ValidateTokenResponseDTO verifyCode(VerifyCodeRequestDTO req);
    ResetPasswordResponseDTO resetPassword(ResetPasswordRequestDTO req);
}

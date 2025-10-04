package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.PasswordChangeDTO;
import com.api.ecoshieldwebservice.dtos.PasswordResetRequestDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioLoginDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioRegisterDTO;

public interface IAuthServices {
    UsuarioRegisterDTO register(UsuarioRegisterDTO usuarioRegisterDTO);
    UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO);
    PasswordResetRequestDTO resetPassword(PasswordResetRequestDTO passwordResetRequestDTO);
    PasswordChangeDTO changePassword(PasswordChangeDTO passwordChangeDTO);
}

package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.PasswordChangeDTO;
import com.api.ecoshieldwebservice.dtos.PasswordResetRequestDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioLoginDTO;
import com.api.ecoshieldwebservice.dtos.UsuarioRegisterDTO;

public interface IAuthServices {
    public UsuarioRegisterDTO register(UsuarioRegisterDTO usuarioRegisterDTO);
    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO);
    public PasswordResetRequestDTO resetPassword(PasswordResetRequestDTO passwordResetRequestDTO);
    public PasswordChangeDTO changePassword(PasswordChangeDTO passwordChangeDTO);
}

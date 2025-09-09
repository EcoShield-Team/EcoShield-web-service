package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.*;

import java.util.List;

public interface IUsuarioServices {
    public UsuarioRegisterDTO register(UsuarioRegisterDTO usuarioRegisterDTO);
    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO);
    public PasswordResetRequestDTO resetPassword(PasswordResetRequestDTO passwordResetRequestDTO);
    public PasswordChangeDTO changePassword(PasswordChangeDTO passwordChangeDTO);

    public UsuarioProfileDTO findById(Integer id);
    public UsuarioProfileDTO updateProfile(Integer id, UsuarioProfileDTO usuarioProfileDTO);
    public List<UsuarioResponseDTO> findAll();
}

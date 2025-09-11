package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.*;
import com.api.ecoshieldwebservice.interfaces.IAuthServices;
import com.api.ecoshieldwebservice.interfaces.IUsuarioServices;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService  implements IAuthServices {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsuarioRegisterDTO register(UsuarioRegisterDTO usuarioRegisterDTO) {
        return null;
    }

    @Override
    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO) {
        return null;
    }

    @Override
    public PasswordResetRequestDTO resetPassword(PasswordResetRequestDTO passwordResetRequestDTO) {
        return null;
    }

    @Override
    public PasswordChangeDTO changePassword(PasswordChangeDTO passwordChangeDTO) {
        return null;
    }
}

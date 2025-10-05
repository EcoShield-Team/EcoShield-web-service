package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.user.UsuarioProfileDTO;
import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseDTO;
import com.api.ecoshieldwebservice.dtos.request.UsuarioUpdateDTO;
import com.api.ecoshieldwebservice.enums.RolNombre;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUsuarioService {
    UsuarioProfileDTO findById(Long id);
    List<UsuarioResponseDTO> findAll();
    UsuarioProfileDTO updateProfile(Long id, UsuarioUpdateDTO dto, MultipartFile imagen);
    void eliminarUsuario(Long id);
    UsuarioResponseDTO asignarRol(Long id, RolNombre nuevoRol);
    void marcarOnline(String correo);
    void marcarOffline(String correo);
}
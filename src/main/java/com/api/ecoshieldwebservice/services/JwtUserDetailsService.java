package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.enums.UsuarioEstado;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario u = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("No existe: " + correo));

        String springRole = "ROLE_" + u.getRol().getRolNombre().name();

        boolean disabled = u.getUsuarioEstado() != UsuarioEstado.ACTIVO;
        boolean locked = u.getUsuarioEstado() == UsuarioEstado.BLOQUEADO;

        return User.withUsername(u.getUsuarioCorreo())
                .password(u.getUsuarioContrasena())
                .authorities(new SimpleGrantedAuthority(springRole))
                .accountLocked(locked)
                .disabled(disabled)
                .build();
    }
}
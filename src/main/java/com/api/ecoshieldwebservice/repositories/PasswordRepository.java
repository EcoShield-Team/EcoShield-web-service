package com.api.ecoshieldwebservice.repositories;


import com.api.ecoshieldwebservice.entities.Password;
import com.api.ecoshieldwebservice.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    Optional<Password> findByToken(String token);
    List<Password> findAllByUsuarioAndUsedAtIsNull(Usuario u);
    void deleteAllByExpiresAtBefore(java.time.OffsetDateTime cutoff);

}

package com.api.ecoshieldwebservice.util;


import com.api.ecoshieldwebservice.entities.Password;
import com.api.ecoshieldwebservice.entities.Usuario;

import java.time.OffsetDateTime;
import java.util.UUID;

public class PasswordUtil {

    private PasswordUtil() {}

    public static Password createToken(Usuario usuario, long hoursToExpire) {
        OffsetDateTime now = OffsetDateTime.now();

        Password token = new Password();
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        token.setCreatedAt(now);
        token.setExpiresAt(now.plusHours(hoursToExpire));
        return token;
    }

    public static boolean isExpired(Password token) {
        return OffsetDateTime.now().isAfter(token.getExpiresAt());
    }

    public static boolean isUsed(Password token) {
        return token.getUsedAt() != null;
    }

}

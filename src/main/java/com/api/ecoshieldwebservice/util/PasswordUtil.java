package com.api.ecoshieldwebservice.util;

import com.api.ecoshieldwebservice.entities.Password;
import com.api.ecoshieldwebservice.entities.Usuario;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.UUID;

public class PasswordUtil {

    private PasswordUtil() {}

    private static final SecureRandom RANDOM = new SecureRandom();

    public static Password createToken(Usuario usuario, long minutesToExpire) {
        OffsetDateTime now = OffsetDateTime.now();

        String tokenValue = UUID.randomUUID().toString();

        int codeInt = RANDOM.nextInt(100000,1000000);
        String verificationCode = String.format("%06d", codeInt);

        Password token = new Password();
        token.setToken(tokenValue);
        token.setVerificationCode(verificationCode);
        token.setUsuario(usuario);
        token.setCreatedAt(now);
        token.setExpiresAt(now.plusMinutes(minutesToExpire));
        return token;
    }

    public static boolean isExpired(Password token) {
        return OffsetDateTime.now().isAfter(token.getExpiresAt());
    }

    public static boolean isUsed(Password token) {
        return token.getUsedAt() != null;
    }
}

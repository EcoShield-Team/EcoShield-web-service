package com.api.ecoshieldwebservice.util;

import com.api.ecoshieldwebservice.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    private final JwtProperties props;

    public JwtUtil(JwtProperties props) { this.props = props; }

    private SecretKey key;
    private Duration expiration;

    @PostConstruct
    void init() {
        byte[] keyBytes = Decoders.BASE64.decode(props.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expiration = Duration.ofMinutes(props.getExpirationMinutes());
    }

    public String generateToken(String correo, String role) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(expiration)))
                .claim("role", role)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token, String expectedCorreo) {
        try {
            Claims c = parseClaims(token);
            boolean notExpired = c.getExpiration().toInstant().isAfter(Instant.now());
            return notExpired && expectedCorreo.equals(c.getSubject());
        } catch (Exception e) {
            return false;
        }
    }

    public String extractCorreo(String token) {
        return parseClaims(token).getSubject();
    }

    public String extractRol(String token) {
        return parseClaims(token).get("role", String.class);
    }

    public Date extractExpiration(String token) {
        return parseClaims(token).getExpiration();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
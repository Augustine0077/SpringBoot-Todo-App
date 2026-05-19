package com.sum.Calculator.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private static final String SECRET = "calculator-demo-secret-key-please-change-123456";
    // time i setted here
    private static final long EXPIRATION_MS = 1000 * 60;
    // there are 3 depndacys needed ;
    //jasowebtoken
    //jjwt-impl
    //jakson-- therse are the depndacys need to add
    // Keys.hmacShaKeyFor  - here the key is encrypted
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
            return true;
        } catch (JwtException exception) {
            return false;
        }
    }
}

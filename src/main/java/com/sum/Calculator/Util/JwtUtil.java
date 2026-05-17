package com.sum.Calculator.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRECT = "";
    private final int EXPIRATION = 1000 * 60 ;
    private final Key secretkey = Keys.hmacShaKeyFor(SECRECT.getBytes(StandardCharsets.UTF_8));
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()))
    }
}

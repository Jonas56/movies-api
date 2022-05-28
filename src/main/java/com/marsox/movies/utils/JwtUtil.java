package com.marsox.movies.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

public class JwtUtil {
    public Jws<Claims> extractClaimsFromToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        try {
            String token = authorization.replace("Bearer ", "");
            String secretKey = "mySecretsecretmySecretsecretmySecretsecretmySecretsecret";
            Jws<Claims> claimsJwts = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            return claimsJwts;
        } catch (JwtException e) {
            throw new IllegalStateException("Token cannot be trusted");
        }
    }
}
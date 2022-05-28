package com.marsox.movies.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

public class JwtUtil {
    public static Jws<Claims> extractClaimsFromToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        try {
            String token = authorization.replace("Bearer ", "");
            String secretKey = "mySecretsecretmySecretsecretmySecretsecretmySecretsecret";
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            throw new IllegalStateException("Token cannot be trusted");
        }
    }
}
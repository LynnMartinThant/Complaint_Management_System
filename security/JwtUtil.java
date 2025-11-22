package com.cms.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private final String secret = "change-this-to-a-strong-secret-key-which-should-be-kept-outside-source";
    private final long expirationMs = 1000L * 60 * 60 * 24; // 24h

    public String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }
}

package com.example.utils;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {

   
    private static final String SECRET = "0123456789ABCDEF0123456789ABCDEF";

    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private JWTUtil() {
        
    }

   
    public static String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

   
    public static Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .setAllowedClockSkewSeconds(60) // small clock skew
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

   
    public static String getUsernameFromToken(String token) {
        return validateToken(token).getSubject();
    }

    public static String getRoleFromToken(String token) {
        return validateToken(token).get("role", String.class);
    }

    public static Date getExpirationFromToken(String token) {
        return validateToken(token).getExpiration();
    }
}

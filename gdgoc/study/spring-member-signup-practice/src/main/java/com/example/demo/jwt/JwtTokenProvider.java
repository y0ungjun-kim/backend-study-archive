package com.example.demo.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtTokenProvider {

    private final String secretKey = "mysecretkeymysecretkeymysecretkeymysecretkey";
    private final long expiration = 1000 * 60 * 60;

    public String createToken(Long memberId, String email) {
        SecretKey key = Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .subject(email)
                .claim("memberId", memberId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
}
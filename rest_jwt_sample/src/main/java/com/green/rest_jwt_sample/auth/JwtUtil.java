package com.green.rest_jwt_sample.auth;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final SecretKey accessKey = Keys.hmacShaKeyFor("access-secret-key-12345678901234567890".getBytes());
    private final SecretKey refreshKey = Keys.hmacShaKeyFor("refresh-secret-key-12345678901234567890".getBytes());

    // 3분짜리 액세스 토큰 생성
    public String createAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)) // 3분
                .signWith(accessKey, Jwts.SIG.HS256)
                .compact();
    }

    // 10분짜리 리프레시 토큰 생성
    public String createRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10분
                .signWith(refreshKey, Jwts.SIG.HS256)
                .compact();
    }

    // 액세스 토큰 검증
    public String validateAccessToken(String token) {
        JwtParser parser = Jwts.parser().verifyWith(accessKey).build();
        return parser.parseSignedClaims(token).getPayload().getSubject();
    }

    // 리프레시 토큰 검증
    public String validateRefreshToken(String token) {
        JwtParser parser = Jwts.parser().verifyWith(refreshKey).build();
        return parser.parseSignedClaims(token).getPayload().getSubject();
    }
}

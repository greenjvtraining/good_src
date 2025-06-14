package com.green.rest_jwt.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {

	private SecretKey secretKey;
	
	public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
		this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
							Jwts.SIG.HS256.key().build().getAlgorithm());
		System.out.println("secretKey : " + secretKey.toString() + ", algorithm : " 
							+ secretKey.getAlgorithm());
	}
	
	public String getUsername(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
				.getPayload().get("username", String.class);
	}
	
	public String getRole(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
				.getPayload().get("role", String.class);
	}
	
	public String getName(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
				.getPayload().get("name", String.class);
	}
	
	public Boolean isExpired(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
				.getPayload().getExpiration().before(new Date());
	}
	
	public String createJwt(String username) {
		return Jwts.builder()
				   .claim("username", username)
				   .issuedAt(new Date(System.currentTimeMillis()))
				   .signWith(secretKey)
				   .compact();
	}
	
	public String createJwt(String username, String name, String role, Long expiredMs) {
		return Jwts.builder()
				   .claim("username", username)
				   .claim("name", name)
				   .claim("role", role)
				   .issuedAt(new Date(System.currentTimeMillis()))
				   .expiration(new Date(System.currentTimeMillis() + expiredMs))
				   .signWith(secretKey)
				   .compact();
	}
}

package com.green.rest_jwt_sample.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.rest_jwt_sample.auth.JwtUtil;
import com.green.rest_jwt_sample.domain.TokenInfo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        // 예시: 실제로는 DB 검증 필요
        if ("user".equals(username) && "pass".equals(password)) {
            String accessToken = jwtUtil.createAccessToken(username);
            String refreshToken = jwtUtil.createRefreshToken(username);
            return ResponseEntity.ok(new TokenInfo(accessToken, refreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenInfo> refresh(@RequestBody Map<String, String> body) {
    	System.out.println("refresh...............");
    	
        String refreshToken = body.get("refreshToken");
        System.out.println("refreshToken : " + refreshToken);
        try {
            String username = jwtUtil.validateRefreshToken(refreshToken);
            String newAccessToken = jwtUtil.createAccessToken(username);
            System.out.println("newAccessToken : " + newAccessToken);
            return ResponseEntity.ok(new TokenInfo(newAccessToken, refreshToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/auth/secure1")
    public ResponseEntity<String> secureEndpoint(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok("Hello " + username + ", you are authenticated!");
    }
    
    @GetMapping("/auth/secure2")
    public ResponseEntity<String> secureEndpoint2(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return ResponseEntity.ok("Hello " + username + ", you are authenticated, too!");
    }
}
package com.example.authServ.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authServ.auth.CustomUserDetails;
import com.example.authServ.dto.LoginDto;
import com.example.authServ.utils.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    
    public LoginController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    	this.authenticationManager = authenticationManager;
    	this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
    	System.out.println("login............." + loginDto);
        try {
            // 사용자 인증
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		loginDto.getUsername(),
                		loginDto.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            System.out.println(user.getUsername());
            // JWT 토큰 생성
            String token = jwtUtil.createJwt(user.getUsername(), user.getRole(), 24*60*60*1000L);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

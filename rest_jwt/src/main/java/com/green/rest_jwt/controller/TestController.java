package com.green.rest_jwt.controller;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.rest_jwt.dto.LoginDto;
import com.green.rest_jwt.utils.JWTUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class TestController {
	
	@Value("${spring.jwt.secret}")
	private String secretKey;
	
	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping("/login")
	public String login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws JsonProcessingException {
		if (loginDto.getUsername().equals("user01") && loginDto.getPassword().equals("1234")) {
			
			//String token = makeToken(loginDto);
			String token = makeJwtToken(loginDto);
			
			response.setHeader("userAuth", token);

			return "good";
		} else {
			return "bad";
		}
	}

	private String makeToken(LoginDto loginDto) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jwt = mapper.writeValueAsString(loginDto);
		String token = "Bearer " + jwt;
		System.out.println("token : " + token);
		
		return token;
	}

	private String makeJwtToken(LoginDto loginDto) {
		
		String jwt = jwtUtil.createJwt(loginDto.getUsername());
		String token = "Bearer " + jwt;
		return token;
	}
	
	@GetMapping("/user")
	public String getMembersPage(HttpServletRequest request) {
		String userAuth = request.getHeader("userAuth");
		if (userAuth == null || userAuth.isEmpty()) {
			return "bad";
		} else {
			String token = userAuth.split(" ")[1];
			//String username = jwtUtil.getUsername(token);
			SecretKey sk = Keys.hmacShaKeyFor(secretKey.getBytes());
			Claims claims = Jwts.parser()
				    .verifyWith(sk)  // verifyWith()로 검증 키 설정
				    .build()               // JwtParser 객체 생성
				    .parseSignedClaims(token)  // 서명 검증 후 Claims 추출
				    .getPayload();         // 페이로드(클레임) 얻기
			return "meber is good!! username : " + claims.get("username", String.class);
			//Claims 인터페이스는 JWT 표준 클레임(Registered Claims)을 위한 메서드(getSubject(), getIssuer(), getExpiration() 등)만 기본 제공합니다.
			//커스텀 클레임(예: username, role, userId 등)은 get(key, class)로 접근해야 합니다.
		}
	}
}

package com.example.sec_jwt.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sec_jwt.auth.CustomUserDetails;
import com.example.sec_jwt.auth.JWTUtil;
import com.example.sec_jwt.entity.Member;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter{

	private final JWTUtil jwtUtil;
	
	public JWTFilter(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("*************************");
		String authorization = request.getHeader("Authorization");
		
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			System.out.println("token null");
			filterChain.doFilter(request, response);
			
			return;
		}
		
		System.out.println("authorization now");
		
		String token = authorization.split(" ")[1];
		System.out.println("token : " + token);
		
		if(jwtUtil.isExpired(token)) {
			System.out.println("token expired");
			filterChain.doFilter(request, response);
			
			return;
		}
		
		String username = jwtUtil.getUsername(token);
		String role = jwtUtil.getRole(token);
		System.out.println("token's username : " + username);
		System.out.println("token's role : " + role);
		
		Member userEntity = new Member();
        userEntity.setUsername(username);
        userEntity.setPassword("temppassword");
        userEntity.setRole(role);
		
      //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

				//스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
				//세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
		
        // 필수: 요청을 다음 필터로 전달
        
	    filterChain.doFilter(request, response);
		
	}

}

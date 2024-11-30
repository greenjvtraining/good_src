package com.example.secu2.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println(authException.getMessage());
		// 요청에 대한 맞춤 처리
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 설정
        response.sendRedirect("/error/401?msg=" + authException.getMessage());
        //response.getWriter().write("Custom 401 error message: Unauthorized access."); // 커스텀 메시지 반환
	}
}

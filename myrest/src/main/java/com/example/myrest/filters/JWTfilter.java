package com.example.myrest.filters;

import java.io.IOException;

import com.example.myrest.entity.Member;
import com.example.myrest.utils.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class JWTfilter extends HttpFilter {

	private JWTUtil jwtUtil;

	public JWTfilter(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("..........JWTfilter........");

		String authorization = request.getHeader("Authorization");
		System.out.println("authorization : " + authorization);
		if (authorization != null && authorization.startsWith("Bearer ")) {
			String jwtToken = authorization.split(" ")[1];
			System.out.println("jwtToken : " + jwtToken);
			if (!jwtUtil.isExpired(jwtToken)) {
				filterChain.doFilter(request, response);
				return;
			}
			
		}else {
			// 유효하지 않은 토큰일 경우 401 응답
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Unauthorized: Invalid or missing token");
		}
	}
}

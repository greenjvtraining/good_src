package com.example.filterInterAuth.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
        /* 헤더로 인증하기
		String token = httpRequest.getHeader("Authorize");
        System.out.println("AuthFilter...token >> " + token);
        if (token == null || !token.equals("valid-token")) {
            response.getWriter().write("Unauthorized");
            return; // 필터 체인 중단
        }
		*/
		// chain.doFilter(request, response); // 다음 필터로 전달
		
		Cookie[] cookies = httpRequest.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("auth".equals(cookie.getName())) {
					String authToken = cookie.getValue();
					System.out.println("Auth Token : " + authToken);
					
					// 유효한 토큰인지 확인하는 로직 (예: JWT 검증 또는 DB 조회)
                    if (isValidAuthToken(authToken)) {
                        chain.doFilter(request, response); // 요청 계속 진행
                        return;
                    }
				}
			}
		}
		// 인증 실패: 401 응답 반환
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("Unauthorized: Invalid or missing authToken.");
       
	}
	// 토큰 유효성 검증 메서드 (예제)
    private boolean isValidAuthToken(String token) {
        // 실제 검증 로직은 JWT 검증, Redis 조회 등으로 대체
        return "authenticated".equals(token);
    }
}

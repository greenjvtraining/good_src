package com.example.filterInterAuth.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SlowModeInterceptor implements HandlerInterceptor{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		boolean slowMode = "true".equalsIgnoreCase(request.getHeader("X-Slow-Mode"));
		
		//느린 모드 활성화 시 메시지 추가
		if(slowMode) {
			//response.getWriter().write("\n\nYou've experienced the nostalgic slow internet mode!");
			System.out.println("You've experienced the nostalgic slow internet mode!");
		}
	}
}

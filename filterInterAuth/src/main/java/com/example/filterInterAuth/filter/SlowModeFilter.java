package com.example.filterInterAuth.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class SlowModeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		//요청시작 시간 기록
		long startTime = System.currentTimeMillis();
		boolean slowMode = "true".equalsIgnoreCase(httpRequest.getHeader("X-Slow-Mode"));
		
		//느린 모드 적용
		if(slowMode) {
			try {
				Thread.sleep(5000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("---------SlowModeFilter pre------------");
		chain.doFilter(request, response);
		
		//요청 처리 시간 출력
		long endTime = System.currentTimeMillis();
		System.out.println("Request processed in " + (endTime - startTime) + " ms");
		System.out.println("-----------SlowModeFilter post------------");
	}
	
	@Override
	public void destroy() {
		
	}

}

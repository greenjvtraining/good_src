package com.example.xxx.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class SecondFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long startTime = System.currentTimeMillis();
		System.out.println("Second Filter is processing the request(2)");
        // 다음 필터나 서블릿 호출
        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Second Filter in " + duration + " ms(2)");
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}

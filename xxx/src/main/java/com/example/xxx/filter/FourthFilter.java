package com.example.xxx.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
@Order(4)
@WebFilter("/api/test")
public class FourthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long startTime = System.currentTimeMillis();
		System.out.println("Fourth Filter..............(4)");
		
		chain.doFilter(request, response);
		
		long duration = System.currentTimeMillis() - startTime;
        System.out.println("Fourth Filter processed in " + duration + " ms(4)");
	}

}

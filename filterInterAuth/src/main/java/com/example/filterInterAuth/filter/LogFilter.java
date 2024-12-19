package com.example.filterInterAuth.filter;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Log Filter : init");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Log Filter : doFilter");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        
        String refferer = httpServletRequest.getHeader("Referer");
        System.out.println("refferer : " + refferer);
        
        // 요청의 추적을 위해 UUID 사용
        String uuid = UUID.randomUUID().toString();
        request.setAttribute("logId", uuid);
        
        try {
            log.info("Log Filter : doFilter : REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("Log Filter : doFilter : RESPONSE [{}][{}]", uuid, requestURI);
        }
	}
	
	@Override
    public void destroy() {
        log.info("Log Filter destroy");
    }

}

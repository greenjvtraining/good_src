package com.green.rest_jwt_sample.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.green.rest_jwt_sample.auth.JwtUtil;
import com.green.rest_jwt_sample.filter.JwtAuthFilter;

@Configuration
public class WebConfig {

	@Bean
	public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil) {
	    return new JwtAuthFilter(jwtUtil);
	}

	@Bean
	public FilterRegistrationBean<JwtAuthFilter> jwtFilter(JwtAuthFilter jwtAuthFilter) {
	    FilterRegistrationBean<JwtAuthFilter> regBean = new FilterRegistrationBean<>();
	    regBean.setFilter(jwtAuthFilter);
	    regBean.addUrlPatterns("/api/auth/*");
	    return regBean;
	}
}
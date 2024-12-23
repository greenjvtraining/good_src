package com.example.myrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.myrest.filters.JWTfilter;
import com.example.myrest.utils.JWTUtil;

@Configuration
public class FilterConfig{

	@Autowired
	private JWTUtil jwtUtil;

	@Bean
	public FilterRegistrationBean<JWTfilter> jwtFilterRegistration() {
		FilterRegistrationBean<JWTfilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JWTfilter(jwtUtil)); // JWTfilter가 Filter를 구현해야 함
		registrationBean.addUrlPatterns("/api/*");
		//registrationBean.setName("JWTfilter");
		//registrationBean.setOrder(1); // 실행 순서
		return registrationBean;
	}
}

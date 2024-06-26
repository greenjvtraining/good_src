package com.example.xxx.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.xxx.filter.LoggingFilter;
import com.example.xxx.filter.SecondFilter;
import com.example.xxx.filter.ThirdFilter;

@Configuration
public class FilterConfig {

	
	
	
	@Bean
    FilterRegistrationBean<ThirdFilter> ThirdFilter() {
        FilterRegistrationBean<ThirdFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new ThirdFilter());
        registrationBean.addUrlPatterns("/api/test"); // 필터를 적용할 URL 패턴 지정
        registrationBean.setOrder(3); // 우선순위 지정, 숫자가 작을수록 높은 우선순위
        
        return registrationBean;
    }
	
	@Bean
    FilterRegistrationBean<SecondFilter> SecondFilter() {
        FilterRegistrationBean<SecondFilter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new SecondFilter());
        registrationBean.addUrlPatterns("/api/test"); // 필터를 적용할 URL 패턴 지정
        registrationBean.setOrder(2); // 우선순위 지정, 숫자가 작을수록 높은 우선순위
        
        return registrationBean;
    }
	
	@Bean
	FilterRegistrationBean<LoggingFilter> loggingFilter(){
		FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
		
		registrationBean.setFilter(new LoggingFilter());
		registrationBean.addUrlPatterns("/api/test");
		registrationBean.setOrder(1); // 우선순위 지정, 숫자가 작을수록 높은 우선순위
		
		return registrationBean;
	}
	
	
}

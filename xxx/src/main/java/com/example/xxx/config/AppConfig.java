package com.example.xxx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.xxx.filter.LoggingInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer{

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor()).addPathPatterns("/api/test");
    }

    @Bean
    public LoggingInterceptor loggingInterceptor() {
    	System.out.println("AppConfig.....loggingInterceptor");
        return new LoggingInterceptor();
    }
}

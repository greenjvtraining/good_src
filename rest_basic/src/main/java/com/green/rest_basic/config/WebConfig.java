package com.green.rest_basic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.upload-dir}")
    private String uploadDir;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
        .allowedOrigins("*")  // 또는 특정 도메인 목록: "http://example.com", "http://localhost:8080"
        .allowedHeaders("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .maxAge(3600);  // pre-flight 요청 캐시 시간 (선택 사항)
				
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/images/**")
	            .addResourceLocations("file:///" + uploadDir);
	}
	
}

package com.example.filterInterAuth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.filterInterAuth.filter.AuthFilter;
import com.example.filterInterAuth.filter.LogFilter;
import com.example.filterInterAuth.filter.SlowModeFilter;
import com.example.filterInterAuth.interceptor.LogInterceptor;
import com.example.filterInterAuth.interceptor.SlowModeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private SlowModeInterceptor slowModeInterceptor;
	
	@Bean
	public FilterRegistrationBean<SlowModeFilter> slowModeFilter(){
		FilterRegistrationBean<SlowModeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		
		filterRegistrationBean.setFilter(new SlowModeFilter());
		filterRegistrationBean.setOrder(-99);
		filterRegistrationBean.addUrlPatterns("/slow");
		
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<LogFilter> logFilter() {
		FilterRegistrationBean<LogFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        // 구현한 필터 등록
		filterRegistrationBean.setFilter(new LogFilter());

        // 필터 순서 지정
		filterRegistrationBean.setOrder(-101);

        // 필터가 적용될 URL 지정
		filterRegistrationBean.addUrlPatterns("/s1");
		filterRegistrationBean.addUrlPatterns("/s2");
		filterRegistrationBean.addUrlPatterns("/iterceptor-test/all-pass");
		filterRegistrationBean.addUrlPatterns("/secure/*");
		
		return filterRegistrationBean;
	}
	
	@Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/secure/*"); // 특정 경로에만 적용
        registrationBean.setOrder(2); // 로깅 필터 다음에 실행
        return registrationBean;
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor())
		        .order(1)
		        .addPathPatterns("/interceptor-test/pass")
		        .excludePathPatterns("/interceptor-test/all-pass");
		
		registry.addInterceptor(slowModeInterceptor)
				.addPathPatterns("/api/**");
	}
	
}

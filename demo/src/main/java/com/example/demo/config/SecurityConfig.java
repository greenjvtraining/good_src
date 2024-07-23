package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf(auth -> auth.disable());
		
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/main", "/images/**", "/css/**", "/js/**", "/memo/**", "/member/**").permitAll()
				.anyRequest().authenticated()
				);
		
		http.formLogin(auth -> auth
					//.loginPage("/login") // 로그인 폼 지정 - 쓰지 않으면 Spring 제공 로그인 폼 사용
					//.loginProcessingUrl("/loginProc") // 로그인 폼 지정 후 폼 정보 보내는 곳 - 이후 스르핑부트에서 알아서 해줌
					.permitAll()
				);
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

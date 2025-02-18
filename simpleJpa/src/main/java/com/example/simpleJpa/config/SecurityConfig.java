package com.example.simpleJpa.config;

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
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf(auth -> auth.disable());
		
		http.formLogin(auth -> auth
				//.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/success", false)
				.permitAll()
				);
		
		http
			.authorizeHttpRequests(auth -> auth
					.anyRequest().permitAll()
					);
		
		return http.build();
	}
}

package com.example.restSwagger_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.restSwagger_1.filters.JWTFilter;
import com.example.restSwagger_1.filters.LoginFilter;
import com.example.restSwagger_1.utils.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JWTUtil jwtUtil;
	private final AuthenticationConfiguration authenticationConfiguration;

	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration
							, JWTUtil jwtUtil
							) {
		this.authenticationConfiguration = authenticationConfiguration;
		this.jwtUtil = jwtUtil;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf(auth -> auth.disable());
		
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/info").permitAll()
						.requestMatchers("/board/**").hasRole("MEMBER")
						.anyRequest().authenticated()
						);
	
		
		http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
		http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
						UsernamePasswordAuthenticationFilter.class);
		http.formLogin(auth -> auth.disable()); // 필요시 로그인 설정
        http.httpBasic(auth -> auth.disable()); // 필요시 HTTP Basic 설정
		
        return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

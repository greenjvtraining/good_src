package com.example.authServ.config;

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

import com.example.authServ.filters.JWTFilter;
import com.example.authServ.filters.LoginFilter;
import com.example.authServ.utils.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
	private final AuthenticationConfiguration authenticationConfiguration;
	private final JWTUtil jwtUtil;
	
	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {

		this.authenticationConfiguration = authenticationConfiguration;
		this.jwtUtil = jwtUtil;
	}

	// AuthenticationManager Bean 등록
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf((auth) -> auth.disable());

		http.formLogin((auth) -> auth.disable());
		/*
		http.formLogin((auth) -> auth
				//.loginPage("/loginForm")
				//.loginProcessingUrl("/login")
				//.defaultSuccessUrl("/")
				.permitAll()
				);
		*/
		//http.httpBasic((auth) -> auth.disable());

		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/loginForm", "/api/auth/login", "/", "/join", "/success").permitAll()
				.requestMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated());

		http
        .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);		
		
		// 필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에
		// authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
		//http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
}

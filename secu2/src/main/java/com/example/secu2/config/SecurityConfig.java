package com.example.secu2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((auth) -> auth
					.requestMatchers("/css/**", "/js/**", "/images/**", "/error/**").permitAll()  // 정적 리소스와 에러 페이지 허용
					.requestMatchers("/", "/regist", "/registProc").permitAll()
					.requestMatchers("/members/**").hasAnyRole("ADMIN", "MEMBER")
					.requestMatchers("/admin").hasRole("ADMIN")
					.anyRequest().authenticated()
					//.anyRequest().permitAll()
					);
		http.exceptionHandling(exception -> exception
	               .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));  // 인증이 필요한 경우 처리
		
		http.formLogin((auth) -> auth
					.loginPage("/login")
					.loginProcessingUrl("/loginProc")
					.defaultSuccessUrl("/members/welcome")
					//.failureUrl("/login?error=true")
					.failureUrl("/fail")
					.permitAll()
					);
		http.csrf(AbstractHttpConfigurer::disable);
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

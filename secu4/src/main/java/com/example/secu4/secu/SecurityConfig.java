package com.example.secu4.secu;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.secu4.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	//@Autowired
	private final DataSource dataSource;
	//@Autowired
	private final MemberService memberService;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
				.authorizeHttpRequests((auth) -> auth
						.anyRequest().permitAll()
						);
		http
				.formLogin((auth) -> auth
						.loginPage("/loginForm")
						.loginProcessingUrl("/loginProc")
						.defaultSuccessUrl("/members/welcome")
						.failureUrl("/fail?error")
						);
		
		http
				.rememberMe((auth) -> auth
						.key("12345678")
						.tokenRepository(persistentTokenRepository())
						.userDetailsService(memberService)
						.tokenValiditySeconds(60*10)
						);
		
		return http.build();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		
		return repo;
	}
}

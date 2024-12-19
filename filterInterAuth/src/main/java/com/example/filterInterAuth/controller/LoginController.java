package com.example.filterInterAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.filterInterAuth.dto.LoginDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@GetMapping("/loginForm")
	public String joinForm() {
		System.out.println("loginForm..........");
		return "loginForm";
	}
	
	@PostMapping("/login")
	public @ResponseBody String login(LoginDto loginDto, HttpServletResponse response) {
		String id = "aaa";
		String pw = "1234";
		System.out.println("login.............");
		if(id.equals(loginDto.getUsername()) && pw.equals(loginDto.getPassword())) {
			response.setHeader("Authorize", "valid-token");
			return "success";
		}
		return "fail";
	}
	
	@PostMapping("/login2")
	public @ResponseBody String login2(LoginDto loginDto, HttpServletResponse response) {
		String id = "aaa";
		String pw = "1234";
		System.out.println("login.............");
		if(id.equals(loginDto.getUsername()) && pw.equals(loginDto.getPassword())) {
			response.setHeader("Authorize", "valid-token");
			Cookie cookie = new Cookie("auth", "authenticated");
			cookie.setHttpOnly(true);          // JavaScript에서 접근 불가 (보안 강화)
			//cookie.setSecure(true);            // HTTPS에서만 전송 (보안 강화)
			cookie.setMaxAge(-1);    // 브라우저 종료시까지 유효
			cookie.setPath("/");               // 모든 경로에서 유효
            
            // 응답에 쿠키 추가
            response.addCookie(cookie);
			
			return "success";
		}
		return "fail";
	}
}

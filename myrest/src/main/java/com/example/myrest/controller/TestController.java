package com.example.myrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myrest.utils.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TestController {

	@Autowired
	private JWTUtil jwtUtil;
	// 필터가 적용되는 경로
	@GetMapping("/api/test")
	public String apiTest(HttpServletRequest request) {
		String token = (String)request.getAttribute("token");
		System.out.println("token : " + token);
		String role = jwtUtil.getRole(token);
		System.out.println(role);
		return "You have accessed a protected API endpoint!";
	}
	
	// 필터가 적용되지 않는 경로
    @GetMapping("/public/test")
    public String publicTest() {
        return "You have accessed a public endpoint!";
    }
}

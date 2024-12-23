package com.example.myrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myrest.dto.LoginDto;
import com.example.myrest.entity.Member;
import com.example.myrest.repository.MemberRepository;
import com.example.myrest.utils.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class MyRestController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("/")
	public ResponseEntity<String> root(){
		System.out.println("Member root...");
		return ResponseEntity.status(HttpStatus.OK).body("root path");
	}
	
	@PostMapping("/member")
	public ResponseEntity<String> regist(@RequestBody Member member){
		System.out.println("Member regist..." + member);
		
		Member savedMember = memberRepository.save(member);
		if(savedMember != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Member Created");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Member Created failed");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(
			@RequestBody LoginDto loginDto,
			HttpServletResponse response){
		System.out.println("login........." + loginDto);
		Member member = memberRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
		
		if(member != null) {
			
			String token = jwtUtil.createJwt(member.getUsername(), member.getName(), member.getRole(), 60*60*1000L);
			response.addHeader("Authorization", "Bearer " + token);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Member Login Success");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Member Login Failed");
	}
	
	@GetMapping("/secure/test0")
	public ResponseEntity<String> test0(HttpServletRequest request){
		String header = request.getHeader("Authorization");
		if(header == null || !header.startsWith("Bearer ")) {
			System.out.println("토큰이 없어요....");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("확인되지 않은 사용자");
		}
		return ResponseEntity.status(HttpStatus.OK).body("good~~~");
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> test(HttpSession session){
		
		return ResponseEntity.status(HttpStatus.OK).body("ok..ok");
	
	}
	
	@GetMapping("/secure")
	public ResponseEntity<String> test2(HttpSession session){
		
		Member member = (Member)session.getAttribute("member");
		if(member != null) {
			System.out.println("test member >> " + member);
			return ResponseEntity.status(HttpStatus.OK).body(member.getUsername());
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("확인되지 않은 사용자");
		
	
	}
}

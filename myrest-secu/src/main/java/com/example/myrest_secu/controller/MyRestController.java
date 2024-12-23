package com.example.myrest_secu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myrest_secu.repository.MemberRepository;

@RestController
public class MyRestController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("/public/test")
	public ResponseEntity<String> publicTest(){
		System.out.println("public .... test....");
		
		return ResponseEntity.status(HttpStatus.OK).body("Public...");
	}
	
	@GetMapping("/member/test")
	public ResponseEntity<String> memberTest(){
		System.out.println("member .... test....");
		
		return ResponseEntity.status(HttpStatus.OK).body("Member...");
	}
	
	@GetMapping("/admin/test")
	public ResponseEntity<String> adminTest(){
		System.out.println("admin .... test....");
		
		return ResponseEntity.status(HttpStatus.OK).body("Admin...");
	}
	
}

package com.example.filterInterAuth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SecureController {
	
	@GetMapping("/secure/sec1")
	public ResponseEntity<String> sec1(HttpServletRequest request) {
		System.out.println("secure >> sec1....");
		
		return ResponseEntity.status(HttpStatus.OK).body("sec1");
		
	}
	
	@GetMapping("/secure/sec2")
	public ResponseEntity<String> sec2(HttpServletRequest request) {
		System.out.println("secure >> sec2....");
		
		return ResponseEntity.status(HttpStatus.OK).body("sec2");
	}
}

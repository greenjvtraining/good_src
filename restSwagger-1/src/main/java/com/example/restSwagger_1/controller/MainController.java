package com.example.restSwagger_1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/")
	public ResponseEntity<?> root(){
		System.out.println("root.......");
		return ResponseEntity.status(HttpStatus.OK).body("root...");
	}
	
	@GetMapping("/info")
	public ResponseEntity<?> info(){
		System.out.println("info.......");
		return ResponseEntity.status(HttpStatus.OK).body("info....");
	}
}

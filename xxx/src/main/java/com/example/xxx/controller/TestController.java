package com.example.xxx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@GetMapping("/test")
    public String testEndpoint() {
		System.out.println("Test Controller......");
        return "Hello, World!";
    }
	
	@GetMapping("/test2")
    public String test2Endpoint() {
        return "Hello, Green!";
    }
}

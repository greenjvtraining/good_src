package com.example.ai_ollama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAiController {

	@GetMapping("/")
	public String root() {
		return "index";
	}
	
}

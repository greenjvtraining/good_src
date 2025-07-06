package com.example.ai_ollama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public RedirectView home() {
		//return "redirect:/talk?message=세계 제일 미녀는 오드리햅번입니다.&model=ollama";
		
		String uri = "/talk";
		String message = "세께 제일 미녀는 오드리헵번입니다.";
		String model = "ollama";
		
		String target = UriComponentsBuilder
				.fromPath(uri)
				.queryParam("message", message)
				.queryParam("model", model)
				.build()
				.encode()
				.toUriString();
		
		return new RedirectView(target);
	}
}

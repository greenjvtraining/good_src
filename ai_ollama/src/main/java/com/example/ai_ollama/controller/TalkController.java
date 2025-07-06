package com.example.ai_ollama.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_ollama.service.TalkService;

@RestController
public class TalkController {

	private final TalkService talkService;
	
	public TalkController(TalkService talkService) {
		this.talkService = talkService;
	}
	
	@GetMapping("/talk")
	public String talk(@RequestParam("message") String message, @RequestParam(name = "model", defaultValue = "ollama") String model) {
		System.out.println("received message param...." + message);
		return talkService.talk(message, model);
	}
}

package com.example.zai02.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zai02.dto.ChatRequest;
import com.example.zai02.dto.ChatResponse;
import com.example.zai02.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

	private final ChatService chatService;
	
	@PostMapping
	public ChatResponse chat(@RequestBody ChatRequest request) {
		String answer = chatService.chat(request.getQuestion());
		
		return new ChatResponse(answer);
	}
}

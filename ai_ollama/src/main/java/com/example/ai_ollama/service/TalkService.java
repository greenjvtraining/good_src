package com.example.ai_ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TalkService {

	private final ChatClient ollamaClient;
	private final ChatClient openaiClient;
	
	public TalkService(
			  @Qualifier("openaiClient") ChatClient openaiClient,
			  @Qualifier("ollamaClient") ChatClient ollamaClient) {
	      this.openaiClient = openaiClient;
	      this.ollamaClient = ollamaClient;
	  }
	
	public String talk(String message, String model) {
		System.out.println("talk Servie message : " + message);
		ChatClient chatClient = model.equalsIgnoreCase("openai") ? openaiClient : ollamaClient;
		
		return chatClient.prompt()
						 .user(message)
						 .call()
						 .content();
	}
}

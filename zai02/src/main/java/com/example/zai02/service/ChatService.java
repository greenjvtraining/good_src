package com.example.zai02.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final ChatClient chatClient;
	
	public ChatService(ChatClient.Builder builder) {

        this.chatClient = builder.build();
    }
	
	public String chat(String question) {
		return chatClient
					.prompt() 		// prompt 생성
					.user(question) // 사용자 질문 추가
					.call()			// OpenAI 호출
					.content();		// 답변 문자열 추출
	}
}

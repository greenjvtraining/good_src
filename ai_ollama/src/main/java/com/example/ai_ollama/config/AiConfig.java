package com.example.ai_ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

	@Bean
	@Qualifier("ollamaClient")
	ChatClient ollamaChatClient(OllamaChatModel ollamChatModel) {
		return ChatClient.builder(ollamChatModel).build();
	}
	
	@Bean
    @Qualifier("openaiClient")
    ChatClient openaiChatClient(OpenAiChatModel openAiChatModel) {
    	return ChatClient.builder(openAiChatModel).build();
    }

}

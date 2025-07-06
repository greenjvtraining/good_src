package com.example.ai_ollama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AiOllamaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiOllamaApplication.class, args);
	}

}

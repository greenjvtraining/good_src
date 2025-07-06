package com.example.ai_ollama.dto;

import lombok.Data;

@Data
public class RequestBbsDto {

	private String title;
	private String content;
	private String writer;
	private String reply;
}

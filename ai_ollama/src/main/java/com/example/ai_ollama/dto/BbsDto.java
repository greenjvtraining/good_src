package com.example.ai_ollama.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsDto {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String reply;
	private String regdate;
}

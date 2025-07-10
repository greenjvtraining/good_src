package com.example.sse.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

	private int no;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdAt;
	
	public Notice(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
}

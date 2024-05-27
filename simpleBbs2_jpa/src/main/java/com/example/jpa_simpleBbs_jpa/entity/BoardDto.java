package com.example.jpa_simpleBbs_jpa.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private List<Reply> replies;
	
	public BoardDto(Long bno, String title, String content, String writer) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
}

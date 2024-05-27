package com.example.jpa_simpleBbs_jpa.entity;

import lombok.Data;

@Data
public class ReplyDto {
	private Integer rno;
	private String writer;
	private String comment;
	private Long bno;
}

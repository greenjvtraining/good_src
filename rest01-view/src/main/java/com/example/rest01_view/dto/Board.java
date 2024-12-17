package com.example.rest01_view.dto;

import lombok.Data;

@Data
public class Board {

	private int bno;
	private String title;
	private String content;
	private String writer;
}

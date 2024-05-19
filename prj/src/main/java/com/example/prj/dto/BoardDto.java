package com.example.prj.dto;

import org.modelmapper.ModelMapper;

import lombok.Data;

@Data
public class BoardDto {
	private int bno;
	private String comment;
	private String originName;
	private String newName;
	private String thumbnailName;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static BoardDto of(Board board) {
		return modelMapper.map(board, BoardDto.class);
	}
}

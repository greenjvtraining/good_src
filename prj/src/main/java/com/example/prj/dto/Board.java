package com.example.prj.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Board {
	//private int bno;
	private String comment;
	private MultipartFile file;
	
	public String getFileName() {
		return file.getOriginalFilename();
	}
}

package com.example.test.dto;

import org.springframework.web.multipart.MultipartFile;

public class BoardDto {
	private String title;
	private String content;
	private MultipartFile imgfile;
	
	public String getFileName() {
		return imgfile.getOriginalFilename();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getImgfile() {
		return imgfile;
	}
	public void setImgfile(MultipartFile imgfile) {
		this.imgfile = imgfile;
	}
	
}

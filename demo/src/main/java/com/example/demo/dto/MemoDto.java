package com.example.demo.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoDto {

	private Integer mno;
	private String writer;
	private String title;
	private String content;
	private MultipartFile photo;
	private LocalDateTime regdate;
}

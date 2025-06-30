package com.green.rest_basic.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	private int uno;
	private String name;
	private int age;
	private String msg;
	private MultipartFile imagefile;
}

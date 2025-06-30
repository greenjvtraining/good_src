package com.green.rest_basic.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserRegistrationDto {
	private String name;
    private int age;
    private String msg;
    private MultipartFile image;
}

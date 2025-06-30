package com.green.rest_basic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private int uno;
	private String name;
	private int age;
	private String msg;
	private String imageUrl;
}

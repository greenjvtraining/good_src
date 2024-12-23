package com.example.myrest_secu.dto;

import lombok.Data;

@Data
public class MemberDto {
	private String username;
	private String password;
	private String name;
	private String role;
}

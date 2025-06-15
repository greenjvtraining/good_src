package com.green.rest_jwt.entity;

import lombok.Data;

@Data
public class Member {
	private String username;
	private String password;
	private String name;
	private String role;
}

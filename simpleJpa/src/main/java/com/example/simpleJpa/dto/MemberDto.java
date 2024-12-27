package com.example.simpleJpa.dto;

import com.example.simpleJpa.entity.MemberEntity;

import lombok.Data;

@Data
public class MemberDto {
	private String username;
	private String password;
	private String role;
	private String name;
	
	public MemberEntity memberToEntity(MemberDto member) {
		MemberEntity entity = new MemberEntity();
		entity.setUsername(member.getUsername());
		entity.setPassword(member.getPassword());
		entity.setName(member.getName());
		
		return entity;
	}
}

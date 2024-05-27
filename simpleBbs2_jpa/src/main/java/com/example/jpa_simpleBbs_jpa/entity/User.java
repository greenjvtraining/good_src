package com.example.jpa_simpleBbs_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private String username;
	@Column(length = 12, nullable = false)
	private String password;
	private String name;
	private String tel;
	
	public User(String username) {
		this.username = username;
	}
}

package com.example.restful.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tbl_member")
@Data
public class Member {
	@Id
	private String username;
	private String password;
	private String name;
	private String role;
}

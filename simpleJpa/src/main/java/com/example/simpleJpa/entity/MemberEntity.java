package com.example.simpleJpa.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@ToString(exclude = "boards")
public class MemberEntity {

	@Id
	private String username;
	private String password;
	private String role;
	private String name;
	@OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<BoardEntity> boards = new ArrayList<>();
}

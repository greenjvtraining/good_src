package com.example.test4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
	private int pno;
	private String name;
	private String role;
	private String teamName;
}

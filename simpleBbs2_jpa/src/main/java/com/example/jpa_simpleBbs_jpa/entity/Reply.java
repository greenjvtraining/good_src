package com.example.jpa_simpleBbs_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rno;
	private String writer;
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "bno")
	private Board board;
}

package com.green.rest_basic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_message")
@Data
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mno;
	private String message;
	private String writer;
}

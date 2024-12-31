package com.example.restSwagger_1.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "게시 정보")
@Data
public class BoardDto {
	@Schema(description = "게시글 번호(unique)", example = "1L")
	private Long bno;
	@Schema(description = "게시글 제목", example = "반갑습니다.")
	private String title;
	private String content;
	private String writer;
	private LocalDateTime registDate;
	private LocalDateTime modifyDate;
}

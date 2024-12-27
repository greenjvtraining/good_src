package com.example.simpleJpa.dto;

import java.time.LocalDateTime;

import com.example.simpleJpa.entity.BoardEntity;
import com.example.simpleJpa.entity.MemberEntity;

import lombok.Data;

@Data
public class BoardDto {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regdate;
	private LocalDateTime modifydate;
	
	public BoardEntity boardToEntity() {
		MemberEntity member = new MemberEntity();
		member.setUsername(writer);
		return BoardEntity.builder()
						  .bno(bno)
						  .title(title)
						  .content(content)
						  .writer(member)
						  .build();
	}
}

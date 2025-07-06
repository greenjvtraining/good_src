package com.example.ai_ollama.entity;

import com.example.ai_ollama.dto.BbsDto;
import com.example.ai_ollama.dto.RequestBbsDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_bbs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BbsEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;
	private String title;
	private String content;
	private String writer;
	@Lob
	private String reply;
	
	public BbsDto entityToDto() { //db에서 조회결과 얻어오기
		
		return BbsDto.builder()
					 .bno(bno)
					 .title(title)
					 .content(content)
					 .writer(writer)
					 .reply(reply)
					 .regdate(this.getRegdate().toString())
					 .build();
	}
	
	public void dtoToEntity(BbsDto dto) { // db에 저장할 때(수정)
		this.bno = dto.getBno();
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.writer = dto.getWriter();
	}
	
	public void requestDtoToEntity(RequestBbsDto dto) { // db에 저장할 때(신규)
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.writer = dto.getWriter();
		this.reply = dto.getReply();
	}
}

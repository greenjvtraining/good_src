package dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NoticeDto {
	private int nno;
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	
	public NoticeDto(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
}

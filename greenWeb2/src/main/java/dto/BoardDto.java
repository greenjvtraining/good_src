package dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private String filename;
	private Timestamp regdate;
	private Timestamp modifydate;
	
	public BoardDto(String title, String content, String writer, String filename) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.filename = filename;
	}
}

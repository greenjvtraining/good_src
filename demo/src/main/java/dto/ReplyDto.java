package dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyDto {
	private int rno;
	private int nno;
	private String content;
	private String writer;
	private Timestamp regdate;
	
	public ReplyDto(int nno, String content, String writer, Timestamp regdate) {
		this.nno = nno;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
	}
}

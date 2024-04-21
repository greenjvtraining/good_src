package dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Alias("member")
public class MemberDto {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String phone;
	private String addr;
	private String role;
}

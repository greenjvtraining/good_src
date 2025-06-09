package com.green.memo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer member_id;
	
	@Column(length = 50, unique = true)
	@Size(min = 3, max = 50)
	private String username;
	@Column(length = 20) // 데이터베이스 수준에서 검증. flush() 호출 시 DB 반영되며 길이 검증
	@Size(min = 4, max = 20) // 런타임에 검증됨. 애플리케이션 수준에서 검증
	private String password;
	private String name;
	@Column(length = 13)
	@Size(max = 13, message = "전화번호는 최대 13자리까지 입력 가능합니다.")
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.(예: 010-1234-5678)")
	private String phone;
	
}

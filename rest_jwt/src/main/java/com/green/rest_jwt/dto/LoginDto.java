package com.green.rest_jwt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
// 옵션 : ALWAYS/NON_NULL/NON_EMPTY/NON_DEFAULT(기본값과 같은 필드 제외)
public class LoginDto {
	private String username;
	private String password;
}

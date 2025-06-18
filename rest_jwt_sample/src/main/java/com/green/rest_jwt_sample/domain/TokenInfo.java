package com.green.rest_jwt_sample.domain;

import lombok.Data;

@Data
public class TokenInfo {
	private String accessToken;
    private String refreshToken;

    public TokenInfo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

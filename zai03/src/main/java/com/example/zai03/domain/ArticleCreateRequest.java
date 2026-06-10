package com.example.zai03.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreateRequest {
	private String title;
    private String abstractText;
    private String topicName; // 연결할 토픽 이름
}

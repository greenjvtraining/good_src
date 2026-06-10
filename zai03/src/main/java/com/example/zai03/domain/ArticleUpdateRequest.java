package com.example.zai03.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleUpdateRequest {
	private String abstractText; // 수정할 본문
    private String newTopicName; // 새로 변경할 토픽 이름
}

package com.example.zai03.domain;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Node("Topic")
@Getter
@NoArgsConstructor
public class Topic {
	
	@Id // 단일 고유 식별자 (앞서 UNIQUE 제약조건을 건 속성)
	private String name;
	
	public Topic(String name) {
		this.name = name;
	}
}

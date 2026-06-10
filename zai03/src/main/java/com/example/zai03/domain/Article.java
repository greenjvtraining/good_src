package com.example.zai03.domain;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Node("Article")
@Getter
@NoArgsConstructor
public class Article {

	@Id
	private String title;
	
	private String abstractText;// Neo4j 속성(abstract)과 매핑 (카멜케이스 지원)
	
	// 관계 매핑: Article -[:MAPPED_TO]-> Topic
    // 기본 방향은 OUTGOING(나가는 방향)입니다.
	@Relationship(type = "MAPPED_TO", direction = Relationship.Direction.OUTGOING)
	private Topic topic;
	
	public Article(String title, String abstractText, Topic topic) {
        this.title = title;
        this.abstractText = abstractText;
        this.topic = topic;
    }
	
	// 💡 실무형 비즈니스 수정 메서드 추가
    public void update(String abstractText, Topic newTopic) {
        this.abstractText = abstractText;
        this.topic = newTopic; // 관계를 직접 교체
    }
}

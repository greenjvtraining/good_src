package com.example.zai03.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.example.zai03.domain.Topic;

public interface TopicRepository extends Neo4jRepository<Topic, String>{
	// 기본 CRUD(findById, save 등)는 Neo4jRepository가 자동으로 제공합니다.
	
	// 정확히 일치(=)하는 토픽과 연결된 데이터만 삭제
    @Query("MATCH (t:Topic {name: $topicName}) " +
           "OPTIONAL MATCH (a:Article)-[r:MAPPED_TO]->(t) " +
           "DETACH DELETE t, a")
    void deleteByTopicName(@Param("topicName") String topicName);
	
	// 토픽과 연결된 모든 Article, 그리고 그 사이의 관계를 통째로 날리는 쿼리
    @Query("MATCH (t:Topic {name: $topicName}) " +
           "OPTIONAL MATCH (a:Article)-[r:MAPPED_TO]->(t) " +
           "DETACH DELETE t, a")
    void deleteTopicAndConnectedArticles(@Param("topicName") String topicName);
}

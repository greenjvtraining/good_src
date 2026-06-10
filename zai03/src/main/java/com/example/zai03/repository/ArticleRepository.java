package com.example.zai03.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.example.zai03.domain.Article;

public interface ArticleRepository extends Neo4jRepository<Article, String>{
	
	List<Article> findByTopicName(String topicName);
	
	@Query("MATCH (a:Article)-[:MAPPED_TO]->(t:Topic {name: $topicName} " + "RETURN a, collect(t)")
	List<Article> findArticlesByCustomTopic(@Param("topicName") String topicName);
	
	@Query("MATCH (a:Article)-[:MAPPED_TO]->(t:Topic) " +
		   "WHERE toLower(t.name) CONTAINS toLower($topicName) " +
		   "RETURN a, collect(t)")
	List<Article> findArticlesByFlexibleTopic(@Param("topicName") String topicName);
	
	@Query("MATCH (a:Article)-[:MAPPED_TO]->(t:Topic) " + 
	       "WHERE t.name =~ ('(?i).*' + $topicName + '.*') " +
		   "RETURN a, collect(t)")
	List<Article> findArticlesByRegexTopic(@Param("topicName") String topicName);
	
	// 💡 기존 관계를 무조건 끊고 새 관계를 매핑하는 커스텀 업데이트 쿼리
    @Query("MATCH (a:Article {title: $title}) " +
           "OPTIONAL MATCH (a)-[r:MAPPED_TO]->(:Topic) " +
           "DELETE r " + // 기존 화살표만 골라서 삭제
           "WITH a " +
           "MATCH (t:Topic {name: $newTopicName}) " +
           "SET a.abstract = $abstractText " + // 본문 수정
           "MERGE (a)-[:MAPPED_TO]->(t) " +   // 새 화살표 연결
           "RETURN a")
    Article updateArticleWithRelationship(
            @Param("title") String title, 
            @Param("abstractText") String abstractText, 
            @Param("newTopicName") String newTopicName);
}

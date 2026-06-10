package com.example.zai03.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zai03.domain.Article;
import com.example.zai03.domain.ArticleCreateRequest;
import com.example.zai03.domain.ArticleUpdateRequest;
import com.example.zai03.domain.Topic;
import com.example.zai03.repository.ArticleRepository;
import com.example.zai03.repository.TopicRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
	
	private final ArticleRepository articleRepository;
    private final TopicRepository topicRepository;
    
    @Transactional
    public Article registerArticle(ArticleCreateRequest request) {
        // 1. 해당 토픽이 이미 그래프 상에 존재하는지 조회, 없으면 새로 생성
        // (JPA의 영속성 컨텍스트처럼 SDN도 기존 노드를 찾아와야 중복 생성을 막습니다.)
        Topic topic = topicRepository.findById(request.getTopicName())
                .orElseGet(() -> topicRepository.save(new Topic(request.getTopicName())));

        // 2. Article 엔티티 생성 및 연관 관계(Topic) 설정
        Article article = new Article(
                request.getTitle(),
                request.getAbstractText(),
                topic // 생성자나 세터를 통해 관계 주입
        );

        // 3. 저장 (Article 노드가 생성되면서 Topic 노드로 향하는 MAPPED_TO 화살표가 자동 생성됨)
        return articleRepository.save(article);
    }
    
    // Article 업데이트 시 토픽이 변경되어도 기존 토픽과의 관계 유지
    @Transactional
    public Article updateArticle(String title, ArticleUpdateRequest request) {
        // 1. 수정할 기존 Article 노드를 조회
        Article article = articleRepository.findById(title)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 논문입니다."));

        // 2. 새롭게 연결할 토픽 노드 준비 (없으면 새로 생성)
        Topic newTopic = topicRepository.findById(request.getNewTopicName())
                .orElseGet(() -> topicRepository.save(new Topic(request.getNewTopicName())));
        
        /*
        // 3. 데이터가 변경된 새 엔티티 객체 조립
        Article updatedArticle = new Article(
                article.getTitle(),        // ID(PK)는 그대로 유지
                request.getAbstractText(), // 본문 수정
                newTopic                   // 관계 이동 (기존 화살표는 자동으로 끊어짐)
        );

        // 4. 저장 및 반영
        return articleRepository.save(updatedArticle);
        */
        // 3. 기존 엔티티 객체의 값을 메서드를 통해 변경
        article.update(request.getAbstractText(), newTopic);

        // 4. 원래 들고 있던 객체를 그대로 저장 (SDN이 기존 관계를 지우고 새 관계를 맺음)
        return articleRepository.save(article);
    }
    
    // Article 업데이트 시 토픽이 변경되면 기존 토픽과의 관계 끊기
    @Transactional
    public Article updateArticle2(String title, ArticleUpdateRequest request) {
        // 1. 새롭게 연결할 토픽 노드가 없다면 먼저 생성 (중복 방지)
        topicRepository.findById(request.getNewTopicName())
                .orElseGet(() -> topicRepository.save(new Topic(request.getNewTopicName())));

        // 2. 커스텀 쿼리를 통해 관계 청소 및 수정 한 번에 처리
        return articleRepository.updateArticleWithRelationship(
                title, 
                request.getAbstractText(), 
                request.getNewTopicName()
        );
    }
    
    @Transactional
    public void deleteArticle(String title) {
        // SDN이 내부적으로 MATCH (a:Article {title: $title}) DETACH DELETE a 쿼리를 실행합니다.
        // DETACH DELETE는 연결된 화살표를 먼저 다 끊고 노드를 지우라는 명령입니다.
        articleRepository.deleteById(title);
    }
}

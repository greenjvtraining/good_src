package com.example.zai03.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zai03.domain.Article;
import com.example.zai03.domain.ArticleUpdateRequest;
import com.example.zai03.domain.Topic;
import com.example.zai03.repository.ArticleRepository;
import com.example.zai03.repository.TopicRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService {

	private final ArticleRepository articleRepository;
    private final TopicRepository topicRepository;
   
    @Transactional
    public void deleteTopic(String topicName) {
        if (topicName == null || topicName.trim().isEmpty()) {
            throw new IllegalArgumentException("토픽 이름은 공백일 수 없습니다.");
        }
        
        // 완전 일치 매칭 삭제 메서드 호출
        topicRepository.deleteByTopicName(topicName);
    }
}

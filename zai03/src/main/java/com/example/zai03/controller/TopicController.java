package com.example.zai03.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zai03.domain.Article;
import com.example.zai03.domain.ArticleUpdateRequest;
import com.example.zai03.service.TopicService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    // HTTP Method: DELETE
    // 요청 URL 예시: DELETE http://localhost:8080/api/topics/GraphRAG
    @DeleteMapping("/{topicName}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("topicName") String topicName) {
        try {
            topicService.deleteTopic(topicName);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
    }
}
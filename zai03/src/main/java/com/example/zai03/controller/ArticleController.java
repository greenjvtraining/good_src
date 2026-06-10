package com.example.zai03.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zai03.domain.Article;
import com.example.zai03.domain.ArticleCreateRequest;
import com.example.zai03.domain.ArticleUpdateRequest;
import com.example.zai03.repository.ArticleRepository;
import com.example.zai03.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

	private final ArticleRepository articleRepository;
	
	private final ArticleService articleService;
	
	
	@GetMapping("/topic/{topicName}")
	public List<Article> getArticlesByTopic(@PathVariable("topicName") String topicName){
		//return articleRepository.findByTopicName(topicName);
		//return articleRepository.findArticlesByFlexibleTopic(topicName);
		return articleRepository.findArticlesByRegexTopic(topicName);
	}
	
	@PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleCreateRequest request) {
        Article savedArticle = articleService.registerArticle(request);
        return ResponseEntity.ok(savedArticle);
    }
	
	// PUT http://localhost:8080/api/articles/Introduction to GraphRAG Architecture
    @PutMapping("/{title}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable("title") String title,
            @RequestBody ArticleUpdateRequest request) {
        try {
            Article updated = articleService.updateArticle2(title, request);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
	
	@DeleteMapping("/{title}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("title") String title) {
	    articleService.deleteArticle(title);
	    return ResponseEntity.noContent().build();
	}
}

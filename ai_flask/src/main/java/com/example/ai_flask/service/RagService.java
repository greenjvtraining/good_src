package com.example.ai_flask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RagService {

    @Autowired
    private RestTemplate restTemplate;

    private final String FLASK_BASE_URL = "http://localhost:5000";

    // 문서 업로드 (임베딩 저장)
    public String uploadDocument(String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("text/plain; charset=UTF-8"));
        HttpEntity<String> request = new HttpEntity<>(content, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
        		FLASK_BASE_URL + "/embed", request, String.class);
        return response.getBody();
    }

    // 질문하고 답변 받기
    public String askQuestion(String question) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> request = new HttpEntity<>(question, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
        		FLASK_BASE_URL + "/rag", request, String.class);
        return response.getBody();
    }
    
    public String getDocumentList() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                FLASK_BASE_URL + "/list", String.class);

        return response.getBody();
    }
}
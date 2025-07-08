package com.example.ai_flask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_flask.service.RagService;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    @Autowired
    private RagService ragService;

    @PostMapping("/upload")
    public String upload(@RequestBody String content) {
    	System.out.println("upload : " + content);
        return ragService.uploadDocument(content);
    }

    @PostMapping("/ask")
    public String ask(@RequestBody String question) {
    	System.out.println("ask : " + question);
        return ragService.askQuestion(question);
    }
    
    @GetMapping("/docs")
    public String getDocs() {
    	System.out.println("docs....");
    	String result = ragService.getDocumentList();
    	System.out.println("docs...result : " + result);
        return result;
    }
}
package com.example.sse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sse.dto.Notice;
import com.example.sse.service.NoticeService;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@PostMapping
    public ResponseEntity<Notice> createNotice(@RequestBody Notice notice) {
		
		Notice result = noticeService.createNotice(notice);
        return ResponseEntity.ok(result);
    }
}

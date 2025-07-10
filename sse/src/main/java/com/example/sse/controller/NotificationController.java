package com.example.sse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.sse.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/subscribe")
	public SseEmitter subscribe(@RequestParam("userId") String userId) {
		System.out.println("SSE 구독 요청 : " + userId);
		
		return notificationService.subscribe(userId);
	}
	
	@GetMapping("/connections")
    public ResponseEntity<Map<String, Object>> getConnectionInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("activeConnections", notificationService.getActiveConnectionCount());
        return ResponseEntity.ok(info);
    }
	
}

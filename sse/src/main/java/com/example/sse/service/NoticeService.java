package com.example.sse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sse.dto.Notice;

@Service
public class NoticeService {

	@Autowired
	private NotificationService notificationService;
	
	public Notice createNotice(Notice notice) {
        
        // 모든 사용자에게 알림 전송
        notificationService.sendNotificationToAll(notice);
        
        return notice;
    }
}

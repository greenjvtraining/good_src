package com.example.sse.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.sse.dto.Notice;

import jakarta.annotation.PreDestroy;


@Service
public class NotificationService {
	
	private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	private final long timeout = 30L * 1000 * 60;
	
	public SseEmitter subscribe(String userId) {
		// 1. 새로운 SSE 연결 생성
		SseEmitter emitter = new SseEmitter(timeout);
		
		// 2. 기존 연결이 있다면 정리 (중복 연결 방지)
        SseEmitter existingEmitter = emitters.get(userId);
        if (existingEmitter != null) {
            System.out.println("기존 연결 정리: " + userId);
            existingEmitter.complete(); // 기존 연결 종료
        }
        // 3. 새 연결을 맵에 저장
		emitters.put(userId, emitter);
		System.out.println("새 SSE  연결 생성 : " + userId + ", 총 연결 수 : " + emitters.size());
		
		// 4. 연결 종료 시 정리 작업 설정
		emitter.onCompletion(() -> {
			System.out.println("SSE 연결 정상 종료 : " + userId);
			emitters.remove(userId);
			System.out.println("연결 정리 완료, 남은 연결 수 : " + emitters.size());
		});
		
		emitter.onTimeout(() -> {
            System.out.println("SSE 연결 타임아웃: " + userId);
            emitters.remove(userId);
            System.out.println("타임아웃 정리 완료. 남은 연결 수: " + emitters.size());
        });
		
		emitter.onError((throwable) -> {
			System.out.println("SSE 연결 에러 : " + userId + ", " + throwable);
			emitters.remove(userId);
			System.out.println("에러 정리 완료, 남은 연결 수 : " + emitters.size());
		});
		
		try {
			//연결 확인 메시지 전송
			emitter.send(SseEmitter.event()
					.name("connect")
					.data("Connected successfully"));
		} catch (IOException e) {
			System.out.println("초기 연결 메시지 전송 실패" + e);
			emitters.remove(userId);
            throw new RuntimeException("SSE 연결 초기화 실패", e);
		}
		
		return emitter;
	}
	
	public void sendNotificationToAll(Notice notice) {
		System.out.println("전체 알림 전송 시작, 대상 연결 수 : " + emitters.size());
		
		List<String> failedEmitters = new ArrayList<>();
		
		emitters.forEach((userId, emitter) -> {
			try {
				emitter.send(SseEmitter.event()
						.name("notice")
						.data(notice));
				System.out.println("공지사항 알림 전송 성공 - " + userId);
			} catch (IOException e) {
				System.out.println("공지사항 알림 전송 실패 : " + e);
				failedEmitters.add(userId);
			}
		}); 
		
		//실패한 연결 제거
		//failedEmitters.forEach(emitters::remove);
		failedEmitters.forEach(userId -> {
			emitters.remove(userId);
			System.out.println("실패한 연결 정리 : " + userId);
		});
		
		System.out.println("전체 알림 전송 완료. 성공 : " + emitters.size() + ", 실패 : " + failedEmitters.size());
	}

	public int getActiveConnectionCount() {
		return emitters.size();
	}
	
	// 특정 사용자 연결 강제 종료
    public boolean disconnectUser(String userId) {
        SseEmitter emitter = emitters.get(userId);
        if (emitter != null) {
            emitter.complete(); // onCompletion 콜백이 호출되어 자동으로 맵에서 제거됨
            System.out.println("사용자 연결 강제 종료: " + userId);
            return true;
        }
        return false;
    }
    
    // 현재 연결 상태 조회
    public Map<String, Object> getConnectionStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("activeConnections", emitters.size());
        status.put("connectedUsers", new ArrayList<>(emitters.keySet()));
        return status;
    }
    
    // 서버 종료 시 모든 연결 정리
    @PreDestroy
    public void cleanup() {
        System.out.println("서버 종료 - 모든 SSE 연결 정리 시작");
        emitters.values().forEach(SseEmitter::complete);
        emitters.clear();
        System.out.println("모든 SSE 연결 정리 완료");
    }
}

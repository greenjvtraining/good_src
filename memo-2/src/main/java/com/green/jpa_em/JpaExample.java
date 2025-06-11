package com.green.jpa_em;

import java.util.List;

import com.green.jpa_em.entity.User;
import com.green.jpa_em.service.UserService;

public class JpaExample {

	public static void main(String[] args) {
		
		UserService userService = new UserService();
        
        try {
            // 사용자 생성
            userService.createUser("alice", "alice@example.com", 30);
            userService.createUser("bob", "bob@example.com", 25);
            userService.createUser("charlie", "charlie@example.com", 35);
            
            // 사용자 조회
            User user = userService.findUserById(1L);
            
            // 모든 사용자 조회
            List<User> allUsers = userService.findAllUsers();
            allUsers.forEach(System.out::println);
            
            // 조건부 조회
            List<User> adults = userService.findUsersByAge(30);
            
            // 사용자 정보 수정
            userService.updateUser(1L, "alice.updated@example.com");
            
            // 페이징 조회
            List<User> firstPage = userService.findUsersWithPaging(0, 2);
            
            // 사용자명으로 조회
            User foundUser = userService.findUserByUsername("bob");
            
            // 엔티티 상태 데모
            userService.demonstrateEntityStates();
            
            // 사용자 삭제
            userService.deleteUser(2L);
            
        } finally {
            // 리소스 정리
            userService.close();
        }
	}

}

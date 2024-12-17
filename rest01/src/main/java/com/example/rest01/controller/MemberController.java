package com.example.rest01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest01.dao.IMemberDao;
import com.example.rest01.domain.Member;

@RestController
@RequestMapping("/member-api")
public class MemberController {
	
	@Autowired
	private IMemberDao memberDao;
	
	//Get - http://localhost:8082/member-api/member?username=aaa
	//username이 'aaa'인 자료를 주세요.
	@GetMapping("/member")
	public ResponseEntity<?> getMember(@RequestParam(value = "username", required = false)String username) {
		System.out.println("**********************************");
		
		try {
			if(username == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파라미터가 NULL이야");
			}
			if(username.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파라미터 값이 비어있어.");
			}
			
			Member member = memberDao.getMember(username);
			
			if(member == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("없어요");
			}
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
			
		}catch(RuntimeException e) {
			System.out.println("RuntimeException ....." + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에서 문제가 생겼어요.");
		}
		
	}
	
	//Post - http://localhost:8082/member-api/member
	//신규 member 정보를 등록합니다.
	@PostMapping("/member")
	public String postMember(Member member) {
		
		memberDao.regMember(member);
		
		return "post";
	}
	
	//Put - http://localhost:8082/member-api/member
	//기존 member 정보를 수정합니다.
	@PutMapping("/member")
	public String putMember(@RequestBody Member member) {
		
		memberDao.updateMember(member);
		
		return "put";
	}
	
	//Delete - http://localhost:8082/member-api/member?username=aaa
	//member 정보를 삭제합니다.
	@DeleteMapping("/member")
	public String delMember(@RequestParam("username")String username) {
		
		memberDao.delMember(username);
		
		return "del";
	}
	
}

package com.example.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restful.entity.Member;
import com.example.restful.repository.MemberRepository;

@RestController
@RequestMapping("/member-api")
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("/member/{username}")
	public ResponseEntity<?> get(@PathVariable("username")String username){
		
		System.out.println("get..username : " + username);
		//파라미터가 null이다, 없다.
		Member member = memberRepository.findByUsername(username);
		System.out.println("member : " + member);
		
		return ResponseEntity.ok().body(member);
	}
	
	@PostMapping("/member")
	public ResponseEntity<?> post(@RequestBody Member member){
		
		memberRepository.save(member);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/member")
	public ResponseEntity<?> put(Member member){
		
		memberRepository.save(member);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/member")
	public ResponseEntity<?> delete(@RequestParam("username")String username){
		
		memberRepository.deleteById(username);
		
		return ResponseEntity.noContent().build();
	}
}

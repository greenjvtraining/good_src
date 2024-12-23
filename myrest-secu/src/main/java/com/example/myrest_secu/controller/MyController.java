package com.example.myrest_secu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.myrest_secu.dto.MemberDto;
import com.example.myrest_secu.entity.Member;
import com.example.myrest_secu.repository.MemberRepository;

@Controller
public class MyController {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/registForm")
	public String registForm() {
		return "registForm";
	}
	
	@PostMapping("/regist")
	public String regist(MemberDto memberDto, Model model) {
		Member member = new Member();
		String encodedPw = bCryptPasswordEncoder.encode(memberDto.getPassword());
		String role = "ROLE_MEMBER";
		
		member.setUsername(memberDto.getUsername());
		member.setPassword(encodedPw);
		member.setName(memberDto.getName());
		member.setRole(role);
		
		Member result = memberRepository.save(member);
		model.addAttribute("result", result);
		
		return "success";
	}
}

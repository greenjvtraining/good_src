package com.example.simpleJpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.simpleJpa.dto.MemberDto;
import com.example.simpleJpa.service.MemberService;

@Controller
public class MainController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/regMemberForm")
	public String regMemberForm() {
		return "regMemberForm";
	}
	
	@PostMapping("/registMember")
	public String registMember(MemberDto memberDto, Model model) {
		memberService.regist(memberDto);
		
		model.addAttribute("msg", "회원등록 되었습니다.");
		
		return "success";
	}
	
	@GetMapping("/success")
	public String success(Model model) {
		model.addAttribute("msg", "로그인 성공..");
		
		return "success";
	}
}

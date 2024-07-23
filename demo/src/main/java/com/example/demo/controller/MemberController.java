package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/registForm")
	public String registForm() {
		System.out.println("regist Member Form.........");
		
		return "member/member_registForm";
	}
	
	@PostMapping("/member/registProc")
	public String registProc(MemberDto member) {
		System.out.println("Member RegProc.......");
		
		memberService.regMember(member);
		
		return "redirect:/";
	}
}

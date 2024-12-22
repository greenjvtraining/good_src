package com.example.sec_jwt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sec_jwt.dto.JoinDto;
import com.example.sec_jwt.service.MemberService;

@RestController
public class JoinController {

	private final MemberService memberService;

	public JoinController(MemberService memberService) {

		this.memberService = memberService;
	}

	//@GetMapping("/joinForm")
	public String joinForm() {
		System.out.println("joinForm.......");
		return "/joinForm";
	}

	@PostMapping("/joinProc")
	public String joinProcess(JoinDto joinDto) {
		System.out.println("joinProc.......");
		memberService.joinProcess(joinDto);

		return "ok";
	}
}

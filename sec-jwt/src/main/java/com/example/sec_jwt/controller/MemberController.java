package com.example.sec_jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/mypage")
	public String mypage() {
		System.out.println("member Mypage......");
		return "mypage";
	}
}

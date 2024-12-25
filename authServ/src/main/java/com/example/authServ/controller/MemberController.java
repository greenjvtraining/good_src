package com.example.authServ.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
	@GetMapping("/main")
	public String admin() {
		System.out.println("member Main....");
		return "member main";
	}
}

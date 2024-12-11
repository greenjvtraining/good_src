package com.example.secu4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.secu4.entity.Member;

@PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
@RequestMapping("/members")
@Controller
public class MemberController {

	@GetMapping("/welcome")
	public String welcome(@AuthenticationPrincipal Member member,Model model) {
		
		model.addAttribute("username", member.getUsername());
		model.addAttribute("name", member.getName());
		model.addAttribute("role", member.getRole());
		
		return "/members/welcome";
	}
	
}

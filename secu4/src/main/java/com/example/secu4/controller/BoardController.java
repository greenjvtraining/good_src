package com.example.secu4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping("/board")
public class BoardController {

	@GetMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("list", "List페이지입니다.");
		
		return "/board/list";
	}
}

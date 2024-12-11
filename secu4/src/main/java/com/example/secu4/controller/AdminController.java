package com.example.secu4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		
		model.addAttribute("msg", "dashboard 입니다.");
		
		return "/admin/dashboard";
	}
}

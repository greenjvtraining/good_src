package com.example.secu4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.secu4.entity.Member;
import com.example.secu4.service.MemberService;

@Controller
public class MainController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping({"/", "/main"})
	public String root() {
		return "main";
	}
	
	@GetMapping("/registForm")
	public String registForm(@RequestParam(value = "fail", required=false)String fail, Model model) {
		
		if(fail != null) {
			model.addAttribute("msg", "등록이 안되었어요...다시 해봐요...");
		}
		
		return "registForm";
	}
	
	@PostMapping("/regist")
	public String regist(Member member) {
		
		int result = memberService.regist(member);
		
		if(result == 1) {
			return "redirect:/thanks";
		}
		
		return "redirect:/registForm?fail=fail";
	}
	
	@GetMapping("/thanks")
	public String thanks(Model model) {
		
		return "thankPage";
	}
	
	@GetMapping("/loginForm")
	public String loginForm(@RequestParam(value = "error", required = false)String error, Model model) {
		
		if(error != null) {
			model.addAttribute("error", "로그인이 잘 되지 않았어요...다시 해봐요..");
		}
		
		return "loginForm";
	}
	
	@GetMapping("/fail")
	public String fail(@RequestParam("fail")String fail, Model model) {
		
		model.addAttribute("msg", "다시 로그인해야겠는데...괜찮겠어?");
		
		return "fail";
	}
	
	
}

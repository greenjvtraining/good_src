package com.example.secu2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.secu2.dto.MemberDto;
import com.example.secu2.entity.Member;
import com.example.secu2.service.JoinService;

@Controller
public class MyController {

	@Autowired
	private JoinService joinService;
	
	@RequestMapping("/")
	public String root(Model model) {
		System.out.println("root................");
		model.addAttribute("filename", "angry-bull.png");
		return "index";
	}
	
	@GetMapping("/regist")
	public String regist() {
		System.out.println("registForm.......");
		return "registForm";
	}
	
	@PostMapping("/registProc")
	public @ResponseBody String registProc(MemberDto memberDto) {
		Member member = joinService.regist(memberDto);
		System.out.println("save member ok.............");
		if(member == null) {
			return "redirect:/registForm";
		}
		
		return "회원가입 완료됨";
	}
	
	@GetMapping("/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
		System.out.println("login........");
		if (error != null) {
            model.addAttribute("error", "아이디와 패스워드를 확인하세요.");
        }
		return "loginForm";
	}
	
	@GetMapping("/fail")
	public String loginFail() {
		System.out.println("fail.......");
		
		return "fail";
	}
	
	@GetMapping("/error/401")
	public String error401(
			@RequestParam("msg") String msg,
			@RequestHeader(value = "Referer", required = false) String referer, 
            Model model) {
		System.out.println("msg : " + msg);
		model.addAttribute("errorMessage", msg);
        model.addAttribute("referer", referer);
        
		return "/error/401";
	}
}

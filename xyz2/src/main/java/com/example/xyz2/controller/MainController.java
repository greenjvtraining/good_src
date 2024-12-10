package com.example.xyz2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.xyz2.dao.IMemberDao;
import com.example.xyz2.domain.Member;
import com.example.xyz2.utils.AuthSession;
import com.example.xyz2.utils.MemberDaoAuthProvider;
import com.example.xyz2.utils.MemberDetails;
import com.example.xyz2.utils.PasswordEncoder;

@Controller
public class MainController {
	@Autowired
	MemberDaoAuthProvider mdap;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	IMemberDao memberDao;
	
	@Autowired
	AuthSession session;
	
	@RequestMapping({"/", "/main"})
	public String main() {
		return "/main";
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		return "/registForm";
	}
	
	@RequestMapping("/regist")
	public String regist(Member member) {
		int key = (int)(Math.random()*5 + 1);
		System.out.println("key : " + key);
		String encodedPw = passwordEncoder.passwordEncoder(member.getPassword(), key);
		System.out.println("암호화 패스워드 : " + encodedPw);
		
		member.setPassword(encodedPw);
		member.setRole("ROLE_MEMBER");
		
		int result = memberDao.regist(member);
		
		if(result == 1) {
			return "redirect:/loginForm";
		}
		
		return "redirect:/registForm";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/loginForm";
	}
	
	@RequestMapping("/login")
	public String login(RedirectAttributes rttr, @RequestParam("username")String username, @RequestParam("password")String password) {
		System.out.println("username : " + username);
		System.out.println("password : " + password); 
		boolean tf = mdap.loginCheck(username, password);
		
		if(tf) {
			rttr.addFlashAttribute("username", username);
			return "redirect:/members/main";
		}
		
		return "redirect:/main";
	}
	
	@RequestMapping("/members/main")
	public String memberMain(Model model) {
		String username = (String) model.getAttribute("username");
		System.out.println("model에서 꺼낸 username : " + username);
		MemberDetails md = session.getAttribute(username);
		System.out.println("session에서 꺼낸 username : " + md.getUsername());
		
		if(md.getRole().equals("ROLE_MEMBER")) {
			model.addAttribute("username", md.getUsername());
			return "/members/main";
		}
		
		return "redirect:/main";
	}
	
}

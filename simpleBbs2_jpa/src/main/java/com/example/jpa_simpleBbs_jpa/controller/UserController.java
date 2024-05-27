package com.example.jpa_simpleBbs_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpa_simpleBbs_jpa.entity.User;
import com.example.jpa_simpleBbs_jpa.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/regUser")
	public void regUser() {
		System.out.println("regUser.......form");
	}
	
	@PostMapping("/reg")
	public String reg(User user) {
		
		User result = userRepository.save(user);
		System.out.println("save result : " + result);
		
		return "redirect:/user/loginForm";
	}
	
	@RequestMapping("/loginForm")
	public void loginForm() {
		System.out.println("loginForm.....");
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password, Model model) {
		String view = "redirect:/user/fail";
		
		User user = userRepository.findByUsernameAndPassword(username, password);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			
			model.addAttribute("user", user);
			view = "redirect:/board/main";
		}
		
		return view;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/";
	}
}

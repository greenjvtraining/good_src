package com.example.websocketPrj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	@RequestMapping("/")
	public String root() {
		return "MultiChatMain";
	}
	
	@RequestMapping("/ChatWindow")
	public void chatWindow(@RequestParam("chatId") String chatId, Model model) {
		System.out.println("chatWindow........" + chatId);
		model.addAttribute("chatId", chatId);
	}
}

package com.example.filterInterAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {
	
	private int cnt_s1;
	private int cnt_s2;
	
	@GetMapping("/s1")
	public String s1(Model model) {
		cnt_s1++;
		log.info("cnt : {}", cnt_s1);

		String[] messages = {"고마워", "사랑해", "미안해"};
		
		int idx = (int)(Math.random()*3);
		model.addAttribute("msg", messages[idx]);
		
		return "s1";
	}
	
	@GetMapping("/s2")
	public String s2(Model model) {
		cnt_s2++;
		log.info("cnt : {}", cnt_s2);

		String[] messages = {"힘내", "자랑스러워", "멋져"};
		
		int idx = (int)(Math.random()*3);
		model.addAttribute("msg", messages[idx]);
		
		return "s2";
	}
}

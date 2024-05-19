package com.example.prj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {

	@RequestMapping("/p1")
	public void p1(Model model) {
		log.info("p1.........");
		String imagePath = "/images/angry_bull_color.jpg";
		model.addAttribute("imagePath", imagePath);
	}
	
	@RequestMapping("/p2")
	public void p2(Model model) {
		log.info("p2.........");
		String imagePath = "/images/angry_bull_color.jpg";
		model.addAttribute("imagePath", imagePath);
	}
}

package com.green.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.memo.entity.Member;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/regist")
	public String regist(@Valid @ModelAttribute Member member, BindingResult result) {
		//BindingResult가 @ModelAttribute 다음에 오지 않으면, Spring이 연결하지 못해서 자동으로 모델에 담기지 않습니다.
		if(result.hasErrors()) {
			return "memberForm";
		}
		
		return "redirect:/members";
	}
}

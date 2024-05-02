package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.ContentDto;

import jakarta.validation.Valid;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "valid2......";
	}
	@RequestMapping("/insertForm")
	public String insert1() {
		System.out.println("createPage.........");
		return "createPage";
	}
	
	@RequestMapping("/create")
	public String insert2(@ModelAttribute("dto") @Valid ContentDto contentDto, BindingResult result) {
		System.out.println("create...........");
		
		String page = "createDonePage";
		
		if(result.hasErrors()) {
			
			if(result.getFieldError("writer") != null) {
				System.out.println("1: " + result.getFieldError("writer").getDefaultMessage());
			}
			if(result.getFieldError("content") != null) {
				System.out.println("1: " + result.getFieldError("content").getDefaultMessage());
			}
			
			page = "createPage";
		}
		
		return page;
	}
}

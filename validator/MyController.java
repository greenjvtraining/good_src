package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.ContentDto;
import com.example.demo.util.ContentValidator;

import jakarta.validation.Valid;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "Validator(1)";
	}
	
	@RequestMapping("/insertForm")
	public String insert1() {
		return "createPage";
	}
	
	@RequestMapping("/create")
	public String insert2(@ModelAttribute("dto")ContentDto contentDto, BindingResult result) {
		String page = "createDonePage";
		System.out.println(contentDto);
		
		ContentValidator validator = new ContentValidator();
		validator.validate(contentDto, result);
		if(result.hasErrors()) {
			page = "createPage";
		}
	
		return page;
	}
	
	@RequestMapping("/create2")
	public String insert3(@ModelAttribute("dto") ContentDto contentDto, BindingResult result) {
		String page = "createDonePage";
		System.out.println(contentDto);
		
		ContentValidator validator = new ContentValidator();
		validator.validate(contentDto, result);
		
		if(result.hasErrors()) {
			System.out.println("getAllErrors : " + result.getAllErrors());
			
			if(result.getFieldError("writer") != null) {
				System.out.println("1: " + result.getFieldError("writer").getCode());
			}
			if(result.getFieldError("content") != null) {
				System.out.println("2: " + result.getFieldError("content").getCode());
			}
			
			page = "createPage";
		}
		
		return page;
	}
	
	@RequestMapping("create3")
	//@Valid : ContentDto 객체를 유효성 검증하겠다고 명시.
	//ContentDto 객체가 파라미터를 받으면 스프링이 binder변수에 저장된 객체를 통해서 즉시 유효성 검사를 한다.
	//이때 에러가 있으면 BindingResult 객체에 에러 결과를 저장한다.
	public String insert4(@ModelAttribute("dto") @Valid ContentDto contentDto, BindingResult result) {
		String page = "createDonePage";
		System.out.println(contentDto);
		
		//ContentValidator validator = new ContentValidator();
		//validator.validate(contentDto, result);
		
		if(result.hasErrors()) {
			System.out.println("getAllErrors : " + result.getAllErrors());
			
			if(result.getFieldError("writer") != null) {
				System.out.println("1: " + result.getFieldError("writer").getCode());
			}
			if(result.getFieldError("content") != null) {
				System.out.println("2: " + result.getFieldError("content").getCode());
			}
			
			page = "createPage";
		}
		
		return page;
	}
	
	@InitBinder //프로젝트 시작시 먼저 실행 - WebDataBinder 타입 변수에 유효성검증 객체가 프로젝트 시작 때 할당됨.
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ContentValidator());
		// 이후 유효성 검증 객체 필요시 WebDataBinder 객체를 통해 꺼내 쓸 수 있다.
	}
}

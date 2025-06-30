package com.green.rest_basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {
	
	@GetMapping("/")
	public String root() {
		log.info("root.............");
		return "index";
	}
}

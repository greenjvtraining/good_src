package com.example.filterInterAuth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/iterceptor-test")
public class InterCeptorController {

	@GetMapping("/all-pass")
    public String allPass(HttpServletRequest request) {
		
        log.info("REQUEST [{}]", request.getRequestURI());
        
        return "interceptorPassPage";
    }

    @GetMapping("/pass")
    public String pass(HttpServletRequest request, Model model) {
    	
        log.info("REQUEST [{}]", request.getRequestURI());
        
        model.addAttribute("Key1", "Value1");
        model.addAttribute("Key2", "Value2");
        
        return "interceptorPassPage";
    }
}

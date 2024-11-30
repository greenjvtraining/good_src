package com.example.secu2.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

//@Controller
public class CustomErrorController implements ErrorController{

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null && statusCode == 404) {
            return "/error/404";  // 404 에러 페이지로 이동
        }
        return "/error/401";  // 다른 에러에 대한 페이지 처리
	}
}

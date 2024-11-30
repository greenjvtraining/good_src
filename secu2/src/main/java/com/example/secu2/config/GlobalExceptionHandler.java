package com.example.secu2.config;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class) // 모든 예외 처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response,
                                  Model model) {
		System.out.println("GlobalException ..............." + ex.getMessage());
		System.out.println("HttpStatus : " + response.getStatus());
        String referer = request.getHeader("Referer"); // HTTP 헤더에서 Referer 값 직접 가져오기
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("referer", referer); // 이전 페이지 URL 전달
        return "/error/401"; // error.html로 이동
    }
}

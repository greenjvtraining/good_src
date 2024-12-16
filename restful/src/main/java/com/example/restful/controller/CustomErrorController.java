package com.example.restful.controller;

import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CustomErrorController implements ErrorController{
	@RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        int status = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        
        System.out.println("status : " + status + ", errorMessage : " + errorMessage);
        Map<String, Object> errorBody = Map.of(
            "status", status,
            "error", HttpStatus.valueOf(status).getReasonPhrase(),
            "message", errorMessage != null ? errorMessage : "Unexpected error",
            "path", request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI)
        );

        return new ResponseEntity<>(errorBody, HttpStatus.valueOf(status));
    }
}

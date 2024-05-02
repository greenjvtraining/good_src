package com.example.demo.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.dto.ContentDto;

public class ContentValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ContentDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ContentDto dto = (ContentDto)target;
		
//		String sWriter = dto.getWriter();
//		if(sWriter == null || sWriter.trim().isEmpty()) {
//			System.out.println("Writer is null or empty");
//			errors.rejectValue("writer", "trouble");
//		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty");
		String sWriter = dto.getWriter();
		if(sWriter.length() < 3) {
			errors.rejectValue("writer", "writer is too short");
		}
		
//		String sContent = dto.getContent();
//		if(sContent == null || sContent.trim().isEmpty()) {
//			System.out.println("Content is null or empty");
//			errors.rejectValue("content", "trouble");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty");
		
	}

}

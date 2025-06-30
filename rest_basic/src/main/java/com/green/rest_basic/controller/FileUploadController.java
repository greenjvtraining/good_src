package com.green.rest_basic.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.rest_basic.dto.UserRequestDto;
import com.green.rest_basic.entity.User;
import com.green.rest_basic.repository.UserRepository;

@Controller
@RequestMapping("/normal")
public class FileUploadController {

	@Value("${file.upload-dir}")
	private String uploadPath;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String root(Model model) {
		System.out.println("normal......form.......");
		model.addAttribute("requestDto", new UserRequestDto());
		return "/normal/form";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute("requestDto") UserRequestDto requestDto) {
		System.out.println("regist ........" + requestDto);
		User user = new User();
		user.setName(requestDto.getName());
		user.setAge(requestDto.getAge());
		user.setMsg(requestDto.getMsg());
		String filename = requestDto.getImagefile().getOriginalFilename();
		user.setImageUrl("/upload/" + filename);
		
		userRepository.save(user);
		
		File file = new File(uploadPath + filename);
		try {
			requestDto.getImagefile().transferTo(file);
			
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/normal/afterReg";
	}
	
	@GetMapping("/afterReg")
	public String afterReg() {
		System.out.println("afterReg.......");
		return "/normal/afterReg";
	}
	
	@PostMapping("/regist2")
	public String regist2(UserRequestDto requestDto) {
		System.out.println("regist ........" + requestDto);
		User user = new User();
		user.setName(requestDto.getName());
		user.setAge(requestDto.getAge());
		user.setMsg(requestDto.getMsg());
		String filename = requestDto.getImagefile().getOriginalFilename();
		user.setImageUrl("/upload/" + filename);
		
		userRepository.save(user);
		
		File file = new File(uploadPath + filename);
		try {
			requestDto.getImagefile().transferTo(file);
			
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/normal/afterReg";
	}

	@PostMapping("/regist3")
	public @ResponseBody String regist3(UserRequestDto requestDto) {
		System.out.println("regist ........" + requestDto);
		User user = new User();
		user.setName(requestDto.getName());
		user.setAge(requestDto.getAge());
		user.setMsg(requestDto.getMsg());
		String filename = requestDto.getImagefile().getOriginalFilename();
		user.setImageUrl("/upload/" + filename);
		
		userRepository.save(user);
		
		File file = new File(uploadPath + filename);
		try {
			requestDto.getImagefile().transferTo(file);
			
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return "good uploaded!!";
	}

}

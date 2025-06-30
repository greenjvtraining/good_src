package com.green.rest_basic.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.green.rest_basic.dto.UserRegistrationDto;
import com.green.rest_basic.entity.User;
import com.green.rest_basic.repository.UserRepository;
//@CrossOrigin("*")
@RestController
@RequestMapping("/api1")
public class RestApiController_user {

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/{uno}")
	public ResponseEntity<?> user(@PathVariable("uno") int uno) {
		System.out.println("api user ...................");
		//UserDto user = new UserDto("James Dean", 46, "Hello EveryOne!!");
		User user = null;
		
		Optional<User> result = userRepository.findById(uno);
		if(result.isPresent()) {
			user = result.get();
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("없어요~");
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> users(){
		List<User> users = userRepository.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PostMapping("/user/regist")
	public ResponseEntity<?> registerUserV2(
	        @ModelAttribute UserRegistrationDto registrationDto) {
	    // ... 동일한 처리 로직
		System.out.println("regist........." + registrationDto);
		
		MultipartFile imageFile = registrationDto.getImage();

	    try {
	        // 1. 파일 저장
	        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
	        Path filepath = Paths.get(uploadDir, filename);
	        Files.copy(imageFile.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

	        // 2. 사용자 정보 DB 저장
	        User user = new User();
	        user.setName(registrationDto.getName());
	        user.setAge(registrationDto.getAge());
	        user.setMsg(registrationDto.getMsg());
	        user.setImageUrl("/upload/" + filename);  // DB에 저장되는 상대 경로

	        userRepository.save(user);

	        return ResponseEntity.ok(Map.of("message", "업로드 성공"));

	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Map.of("message", "파일 저장 실패"));
	    }
	}
}

package com.green.rest_basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.rest_basic.dto.MessageDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController_message {
	
	List<MessageDto> list = new ArrayList<>();
	
	public RestApiController_message() {
		list.add(new MessageDto(1, "Hello~1", "James"));
		list.add(new MessageDto(2, "Hello~2", "Ann"));
		list.add(new MessageDto(3, "Hello~3", "Yujin"));
		list.add(new MessageDto(4, "Hello~4", "Mojo"));
		list.add(new MessageDto(5, "Hello~5", "Karl"));
	}
	
	@GetMapping("/v1/message/{mno}")
	public ResponseEntity<MessageDto> getMesage(@PathVariable("mno") int mno){
		MessageDto message = list.get(mno);
		
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/v1/messages")
	public ResponseEntity<List<MessageDto>> getMessageList(){
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/v1/message")
	public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto){
		log.info("post..............." + messageDto);
		
		list.add(messageDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(messageDto);
	}

	@PutMapping("/v1/message")
	public ResponseEntity<String> modifyMessage(@RequestBody MessageDto messageDto){
		log.info("modify.........");
		
		list.set(3, messageDto);
		
		return ResponseEntity.status(HttpStatus.OK).body("수정 되었어요.");
	}
	
	@DeleteMapping("/v1/message/{mno}")
	public ResponseEntity<String> deleteMessage(@PathVariable("mno") int mno){
		int idx = -1;
		for(MessageDto msg : list) {
			idx++;
			if(mno == msg.getMno()) {
				break;
			}
		}
		list.remove(idx);
		
		return ResponseEntity.status(HttpStatus.OK).body("삭제되었어요.");
	}
}

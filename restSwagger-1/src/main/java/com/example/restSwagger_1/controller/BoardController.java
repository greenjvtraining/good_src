package com.example.restSwagger_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restSwagger_1.dto.BoardDto;
import com.example.restSwagger_1.service.BoardService;

@RestController
@RequestMapping("/board-manager")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/board")
	public ResponseEntity<?> addBoard(@RequestBody BoardDto boardDto){
		System.out.println("boardController >> addBoard...." + boardDto);
		boardService.addBoard(boardDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("created....");
	}
	
	@GetMapping("/board/{bno}")
	public ResponseEntity<?> getBoard(@PathVariable("bno")Long bno) {
		System.out.println("boardController >> getBoard....");
		BoardDto boardDto = boardService.getBoard(bno);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(boardDto);
	}
}

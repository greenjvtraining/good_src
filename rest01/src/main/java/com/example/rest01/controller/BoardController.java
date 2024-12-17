package com.example.rest01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest01.dao.IBoardDao;
import com.example.rest01.domain.Board;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private IBoardDao boardDao;
	
	@GetMapping("/list")
	public ResponseEntity<List<Board>> getList(){
		System.out.println("rest Server >> getList.....");
		List<Board> list = boardDao.getList();
		
		if(list == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		
		System.out.println("list size : " + list.size());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
	}
	
	@PostMapping("/board")
	public ResponseEntity<Board> regist(@RequestBody Board board){
		System.out.println("REST - regist...." + board);
		boardDao.regist(board);
		
		return ResponseEntity.status(HttpStatus.OK).body(board);
	}
}

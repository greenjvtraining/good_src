package com.example.simpleJpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.simpleJpa.dto.BoardDto;
import com.example.simpleJpa.entity.BoardEntity;
import com.example.simpleJpa.secu.CustomUserDetails;
import com.example.simpleJpa.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<BoardEntity> boardList = boardService.getBoards();
		
		model.addAttribute("boardList", boardList);
		return "/board/boardList";
	}
	
	@GetMapping("/regBoardForm")
	public String regBoardForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		
		String username = customUserDetails.getUsername();
		model.addAttribute("writer", username);
		
		return "/board/regBoardForm";
	}
	
	@PostMapping("/registBoard")
	public String registBoard(BoardDto boardDto, Model model) {
		System.out.println("regist: " + boardDto);
		boardService.regist(boardDto);
		
		model.addAttribute("msg", "게시글 등록 성공...");
		return "/success";
	}
	
	@GetMapping("/{bno}")
	public String boardDetail(@PathVariable("bno")Long bno, Model model) {
		BoardEntity board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		
		return "/board/boardDetail";
	}
	
	@PostMapping("/modify")
	public String boardModify(BoardDto boardDto) {
		boardService.update(boardDto);
		return "redirect:/board/boardList";
	}
}

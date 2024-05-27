package com.example.jpa_simpleBbs_jpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa_simpleBbs_jpa.entity.Board;
import com.example.jpa_simpleBbs_jpa.entity.BoardDto;
import com.example.jpa_simpleBbs_jpa.entity.ReplyDto;
import com.example.jpa_simpleBbs_jpa.entity.User;
import com.example.jpa_simpleBbs_jpa.repository.BoardRepository;
import com.example.jpa_simpleBbs_jpa.service.ReplyService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/main")
	public void main(Model model) {
		List<Board> list = boardRepository.findAll();
		List<BoardDto> blist = new ArrayList<>();
		for(Board board : list) {
			Long bno = board.getBno();
			String title = board.getTitle();
			String content = board.getContent();
			String writer = board.getUser().getUsername();
			
			BoardDto boardDto = new BoardDto(bno, title, content, writer);
			blist.add(boardDto);
			
		}
		model.addAttribute("list", blist);
	}
	
	@RequestMapping("/regBoard")
	public void regBoard() {
		System.out.println("regBoard.........Form..");
	}
	
	@RequestMapping("/reg")
	public String reg(BoardDto boardDto) {
		Board board = new Board(boardDto.getBno(), boardDto.getTitle(), boardDto.getContent(), new User(boardDto.getWriter()));
		Board result = boardRepository.save(board);
		System.out.println("result : " + result);
		
		return "redirect:/board/main";
	}
	
	@GetMapping("/detail/{bno}")
	public String getBoard(@PathVariable("bno") Long bno, Model model) {
		System.out.println("getBoard.......");
		Optional<Board> result = boardRepository.findById(bno);
		
		if(result.isPresent()) {
			Board board = result.get();
			model.addAttribute("board", board);
		}
		
		List<ReplyDto> replyList = replyService.getReplyList(bno);
		model.addAttribute("replyList", replyList);
		
		return "/board/detail";
	}
}

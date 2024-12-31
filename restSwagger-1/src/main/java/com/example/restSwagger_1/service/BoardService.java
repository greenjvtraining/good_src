package com.example.restSwagger_1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restSwagger_1.dto.BoardDto;
import com.example.restSwagger_1.entity.Board;
import com.example.restSwagger_1.entity.Member;
import com.example.restSwagger_1.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public void addBoard(BoardDto boardDto) {
		Board board = new Board();
		Member member = new Member();
		member.setUsername(boardDto.getWriter());
		
		board.setBno(null);
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
		board.setWriter(member);
		
		boardRepository.save(board);
	}
	
	public BoardDto getBoard(Long bno) {
		Optional<Board> result = boardRepository.findById(bno);
		
		Board board = result.get();
		BoardDto boardDto = new BoardDto();
		boardDto.setBno(board.getBno());
		boardDto.setTitle(board.getTitle());
		boardDto.setContent(board.getContent());
		boardDto.setWriter(board.getWriter().getUsername());
		
		return boardDto;
	}
}

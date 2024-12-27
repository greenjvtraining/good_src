package com.example.simpleJpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simpleJpa.dto.BoardDto;
import com.example.simpleJpa.entity.BoardEntity;
import com.example.simpleJpa.entity.MemberEntity;
import com.example.simpleJpa.repository.BoardRepository;
import com.example.simpleJpa.repository.MemberRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void regist(BoardDto boardDto) {
		BoardEntity board = boardDto.boardToEntity();
		System.out.println("regist(entity) : " + board);
		boardRepository.save(board);
	}
	
	public void update(BoardDto boardDto) {
		BoardEntity board = boardDto.boardToEntity();
		boardRepository.save(board);
	}
	
	public void delete(Long bno) {
		boardRepository.deleteById(bno);
	}
	
	public BoardEntity getBoard(Long bno) {
		Optional<BoardEntity> result = boardRepository.findById(bno);
		BoardEntity board = result.get();
		return board;
	}
	
	public List<BoardEntity> getBoards(){
		return boardRepository.findAll();
	}
	
	public List<BoardEntity> getMemberBoards(MemberEntity writer){
		
		return boardRepository.findByWriter(writer);
	}
}

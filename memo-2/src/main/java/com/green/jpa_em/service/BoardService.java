package com.green.jpa_em.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.jpa_em.entity.Board;
import com.green.jpa_em.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public List<Board> getList(){
		
		List<Board> list = boardRepository.findAll();
		
		return list;
	}
	
	public List<Board> getListWithMember(){
		List<Board> list = boardRepository.getBoardListWithMember();
		
		return list;
	}
}

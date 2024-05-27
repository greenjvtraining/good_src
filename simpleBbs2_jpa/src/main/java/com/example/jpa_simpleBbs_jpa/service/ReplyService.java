package com.example.jpa_simpleBbs_jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa_simpleBbs_jpa.entity.Board;
import com.example.jpa_simpleBbs_jpa.entity.Reply;
import com.example.jpa_simpleBbs_jpa.entity.ReplyDto;
import com.example.jpa_simpleBbs_jpa.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	public void replyResist(ReplyDto replyDto) {
		Reply reply = new Reply();
		
		Board board = new Board();
		board.setBno(replyDto.getBno());
		
		reply.setBoard(board);
		reply.setComment(replyDto.getComment());
		reply.setWriter(replyDto.getWriter());
		reply.setRno(replyDto.getRno());
		
		replyRepository.save(reply);
	}
	
	public List<ReplyDto> getReplyList(Long bno){
		Board board = new Board();
		board.setBno(bno);
		List<Reply> replies = replyRepository.findByBoard(board);
		List<ReplyDto> replyList = new ArrayList<>();
		
		for(Reply r : replies) {
			ReplyDto dto = new ReplyDto();
			dto.setBno(r.getBoard().getBno());
			System.out.println("******rno : " + r.getRno());
			dto.setRno(r.getRno());
			dto.setComment(r.getComment());
			dto.setWriter(r.getWriter());
			
			replyList.add(dto);
		}
		
		return replyList;
	}
}

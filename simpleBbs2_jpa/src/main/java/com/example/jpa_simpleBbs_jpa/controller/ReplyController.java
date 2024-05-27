package com.example.jpa_simpleBbs_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jpa_simpleBbs_jpa.entity.Board;
import com.example.jpa_simpleBbs_jpa.entity.ReplyDto;
import com.example.jpa_simpleBbs_jpa.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/regist")
	public @ResponseBody List<ReplyDto> regist(@RequestBody ReplyDto replyDto) {
		System.out.println("reply regist..................");
		
		replyService.replyResist(replyDto);
		
		Board board = new Board();
		board.setBno(replyDto.getBno());
		
		List<ReplyDto> replyList = replyService.getReplyList(board.getBno());
		
		return replyList;
	}
	
	@RequestMapping("/getList")
	public @ResponseBody List<ReplyDto> getList(@RequestParam("bno") Long bno){
		List<ReplyDto> replyList = replyService.getReplyList(bno);
		
		return replyList;
	}
	
	
}

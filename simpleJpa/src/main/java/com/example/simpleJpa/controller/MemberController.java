package com.example.simpleJpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.simpleJpa.entity.BoardEntity;
import com.example.simpleJpa.entity.MemberEntity;
import com.example.simpleJpa.secu.CustomUserDetails;
import com.example.simpleJpa.service.BoardService;
import com.example.simpleJpa.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/mypage")
	public String mypage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		MemberEntity member = new MemberEntity();
		String username = userDetails.getUsername();
		member.setUsername(username);
		
		List<BoardEntity> boards = boardService.getMemberBoards(member);
		
		model.addAttribute("boardList", boards);
		
		return "/member/mypage";
	}
	
	@GetMapping("/mypage2")
	public String mypage2(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		String username = userDetails.getUsername();
		MemberEntity member = memberService.getMember(username);
		
		List<BoardEntity> boards = member.getBoards();
		
		model.addAttribute("boardList", boards);
		return "/member/mypage";
	}
	
	@GetMapping("/mypage3")
	public String mypage3(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		String username = userDetails.getUsername();
		
		List<BoardEntity> boards = memberService.getMemberBoards(username);
		
		model.addAttribute("boardList", boards);
		return "/member/mypage";
	}
}

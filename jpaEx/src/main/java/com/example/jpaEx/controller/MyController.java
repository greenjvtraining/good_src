package com.example.jpaEx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jpaEx.repository.MemoRepository;

@Controller
public class MyController {

	@Autowired
	private MemoRepository memoRepository;
	
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "Memo Prj.....";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", memoRepository.findAll());
		model.addAttribute("cnt", memoRepository.count());
		
		return "list2";
	}
	
	@RequestMapping("/list2")
	public String list2(@RequestParam("pageNum") Long pageNum, Model model) {
		System.out.println("pageNum : " + pageNum);
		model.addAttribute("pageNum", pageNum);
		//model.addAttribute("list", memoRepository.findAll());
		model.addAttribute("list", memoRepository.findByMnoBetweenOrderByMnoDesc((pageNum*10)-9, pageNum*10));
		model.addAttribute("cnt", memoRepository.count());
		
		return "list2";
	}
	
	@RequestMapping("/list3")
	public String list3(@RequestParam("pageNum") Long pageNum, Model model) {
		System.out.println("pageNum : " + pageNum);
		model.addAttribute("pageNum", pageNum);
		//model.addAttribute("list", memoRepository.findAll());
		model.addAttribute("list", memoRepository.findByMnoBetweenOrderByMnoDesc((pageNum*10)-9, pageNum*10));
		model.addAttribute("cnt", memoRepository.count());
		
		return "list3";
	}
	
	@RequestMapping("/list4")
	public String list4(@RequestParam("pageNum") Long pageNum, Model model) {
		//전체 게시글 수
		int totalCnt = (int)memoRepository.count();
		
		//페이지 당 게시글 수 : 10개
		int pagePerboard = 10;
		//페이지 블록에 보여질 페이지 번호 수 : 5개
		int pageCount = 5;
		
		//페이지 블록 시작 페이지 번호
		int start = (int)Math.floor((pageNum-1)/pageCount)*pageCount + 1;
		
		//페이지 블록 끝 페이지 번호 (시작 번호 + 5)
		int end = start + (pageCount - 1);
		
		//실제 페이지 블록 끝번호
		int realEnd = (totalCnt / 10) + 1;
		
		System.out.println("pageNum : " + pageNum);
		model.addAttribute("pageNum", pageNum);
		//model.addAttribute("list", memoRepository.findAll());
		model.addAttribute("list", memoRepository.findByMnoBetweenOrderByMnoDesc((pageNum*10)-9, pageNum*10));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("realEnd", realEnd);
		
		return "list4";
	}
}

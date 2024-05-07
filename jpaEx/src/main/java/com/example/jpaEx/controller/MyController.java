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
		System.out.println("pageNum : " + pageNum);
		model.addAttribute("pageNum", pageNum);
		//model.addAttribute("list", memoRepository.findAll());
		model.addAttribute("list", memoRepository.findByMnoBetweenOrderByMnoDesc((pageNum*10)-9, pageNum*10));
		model.addAttribute("cnt", memoRepository.count());
		
		return "list4";
	}
}

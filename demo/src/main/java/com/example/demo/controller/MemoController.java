package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemoDto;
import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;

@Controller
public class MemoController {

	@Autowired
	private MemoService memoService;
	
	@GetMapping("/memo/registForm")
	public String regist() {
		System.out.println("Memo registForm...........");
		
		return "memo/memo_registForm";
	}
	
	@PostMapping("/memo/registProc")
	public String registProc(MemoDto memo) {
		System.out.println("Memo registProc...........");
		memoService.regMemo(memo);
		
		return "redirect:/memo/list";
	}
	
	@GetMapping("/memo/list")
	public String memoList(Model model) {
		
		System.out.println("memoList..........");
		
		List<Memo> list = memoService.getList();
		model.addAttribute("list", list);
		
		return "memo/memoList";
	}
}

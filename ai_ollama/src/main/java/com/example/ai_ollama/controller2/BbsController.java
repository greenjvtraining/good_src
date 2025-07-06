package com.example.ai_ollama.controller2;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ai_ollama.dto.BbsDto;
import com.example.ai_ollama.dto.RequestBbsDto;
import com.example.ai_ollama.entity.BbsEntity;
import com.example.ai_ollama.service.BbsService;
import com.example.ai_ollama.service.TalkService;

@Controller
@RequestMapping("/bbs")
public class BbsController {

	@Autowired
	private BbsService bbsService;
	
	@Autowired
	private TalkService talkService;
	
	@GetMapping("/regform")
	public String regform(@RequestParam(name = "error", required = false) String error, Model model) {
		
		System.out.println("error param : " + error);
		
		if(error != null) {
			model.addAttribute("error", error);
		}
		
		return "/bbs/regform";
	}
	@PostMapping("/reg")
	public String reg(RequestBbsDto requestBbsDto, RedirectAttributes rttr) {
		
		String response = talkService.talk(requestBbsDto.getContent(), "openai");
		System.out.println("ai 응답 : " +  response);
		//ai 응답 추가
		requestBbsDto.setReply(response);
		//db 등록
		BbsEntity entity = bbsService.regist(requestBbsDto);
		
		if(entity == null) {
			rttr.addFlashAttribute("error", "regist faild....try again..");
			
			return "redirect:/regForm";
		}
		
		return "redirect:/bbs/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<BbsDto> list = bbsService.getList();
		model.addAttribute("list", list);
		
		return "/bbs/list";
	}
	
	@GetMapping("/detail/{bno}")
	public String getBbs(@PathVariable("bno")int bno, Model model) {
		
		BbsDto bbs = bbsService.getBbs(bno);
		model.addAttribute("bbs", bbs);
		
		return "/bbs/detail";
	}
	
}

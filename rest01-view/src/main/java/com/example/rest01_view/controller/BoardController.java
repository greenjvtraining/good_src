package com.example.rest01_view.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.rest01_view.dto.Board;
//view
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final RestTemplate restTemplate;
	
	public BoardController() {
		this.restTemplate = new RestTemplate();
	}
	
	@RequestMapping("/list")
	public String boardList(Model model) {
		
		String url = "http://localhost:8082/board/list";
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		List<Board> list = response.getBody();
		System.out.println("view_server : " + list);
		model.addAttribute("list", list);
		
		return "/board/list";
	}
	
	@RequestMapping("/list2")
	public String boardList2(Model model) {
		WebClient webClient = WebClient.create();
		
		List<Board> list = webClient.get()
								   .uri("http://localhost:8082/board/list")
								   .retrieve()
								   .bodyToMono(new ParameterizedTypeReference<List<Board>>() {})
								   .block();
		
		System.out.println(list);
		model.addAttribute("list", list);
		return "/board/list";
	}
	
	@PostMapping("/regist")
	public String createBoard(Board board, Model model){
		System.out.println("regist Param : " + board);
		WebClient webClient = WebClient.create();
		webClient.post()
						.uri("http://localhost:8082/board/board")
						//.header("Content-Type", "application/json")
						.bodyValue(board)
						.retrieve()
						.bodyToMono(Board.class)
						.subscribe(response -> System.out.println("Response: " + response));
		//System.out.println("regist......" + result);
		return "success";
	}
}

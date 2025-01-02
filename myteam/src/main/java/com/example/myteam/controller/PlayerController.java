package com.example.myteam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myteam.entity.Player;
import com.example.myteam.service.PlayerService;

@Controller
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/registForm")
	public String registPlayerForm() {
		return "/players/registForm";
	}
	
	@PostMapping("/regist")
	public String registPlayer(
					@RequestParam("name")String playerName, 
					@RequestParam("pno")int number, 
					@RequestParam("position")String position, 
					@RequestParam("team")String teamName 
					) {
		String teamLocation = teamName.split(" ")[0];
		playerService.registPlayer(playerName, number, position, teamName, teamLocation);
		
		return "redirect:/players/playerList";
	}
	
	@GetMapping("/playerList")
	public String playerList(Model model) {
		List<Player> playerList = playerService.getList();
		model.addAttribute("playerList", playerList);
		return "/players/playerList";
	}
}

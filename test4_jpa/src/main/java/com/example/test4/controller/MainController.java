package com.example.test4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.test4.entity.Player;
import com.example.test4.entity.Team;
import com.example.test4.repository.PlayerRepository;
import com.example.test4.repository.TeamRepository;

@Controller
public class MainController {

	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@RequestMapping("/")
	public String root() {
		
		return "main";
	}
	
	@RequestMapping("/regTeamForm")
	public void regTeamForm() {
		
	}
	
	@RequestMapping("/registTeam")
	public String registTeam(@RequestParam("teamId") String teamId, @RequestParam("teamName")String teamName) {
		Team team = Team.builder()
				.teamId(teamId)
				.teamName(teamName)
				.build();
		
		System.out.println(teamRepository.save(team));
		
		return "redirect:/teamList";
	}
	
	@RequestMapping("/teamList")
	public String teamList(Model model) {
		List<Team> list = teamRepository.findAll();
		
		model.addAttribute("list", list);
		
		return "teamList";
	}
	
	@RequestMapping("/regPlayerForm")
	public void registPlayerForm() {
		
	}
	
	@RequestMapping("/registPlayer")
	public String registPlayer(@RequestParam("name")String name,
							   @RequestParam("role")String role,
							   @RequestParam("teamId")String teamId,
							   Model model) {
		
		Team team = Team.builder().teamId(teamId).build();
		
		Player player = Player.builder()
				.name(name)
				.role(role)
				.team(team)
				.build();
		
		System.out.println(playerRepository.save(player));
		Optional<Team> result = teamRepository.findById(teamId);
		
		Team team1 = result.get();
		model.addAttribute("team", team1);
		
		return "teamDetail";
	}
	
	
}

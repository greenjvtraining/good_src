package com.example.myteam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myteam.entity.Team;
import com.example.myteam.service.TeamService;

@Controller
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	
	
	@GetMapping("/registForm")
	public String registForm() {
		return "/teams/registForm";
	}
	
	@PostMapping("/regist")
	public String registTeam(@RequestParam("name")String name, @RequestParam("homeTown")String homeTown) {
		
		teamService.registTeam(name, homeTown);
		
		return "redirect:/teams/teamList";
	}
	
	@GetMapping("/teamList")
	public String teamList(Model model) {
		
		List<Team> teamList = teamService.getList();
		model.addAttribute("teamList", teamList);
		
		return "/teams/teamList";
	}
}

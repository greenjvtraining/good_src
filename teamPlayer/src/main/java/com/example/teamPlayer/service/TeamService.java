package com.example.teamPlayer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.teamPlayer.entity.Player;
import com.example.teamPlayer.entity.Team;
import com.example.teamPlayer.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Transactional
	public void regTeam() {
		Team team = new Team();
		team.setName("Totnam");

		Player p1 = new Player();
		p1.setName("Choi");

		Player p2 = new Player();
		p2.setName("Hong");

		team.addPlayer(p1);
		team.addPlayer(p2);

		// 한 번의 save(persist)로 세 엔티티가 모두 insert 됨
		teamRepository.save(team);
	}
}

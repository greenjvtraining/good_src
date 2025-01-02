package com.example.myteam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myteam.entity.Player;
import com.example.myteam.entity.Team;
import com.example.myteam.enums.Position;
import com.example.myteam.repository.PlayerRepository;
import com.example.myteam.repository.TeamRepository;

@Service
@Transactional
public class PlayerService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public void registPlayer(String playerName, int number, String position, String teamName, String teamLocation) {
		// 팀 객체 생성
        Team team = new Team();
        team.setName(teamName);
        team.setHometown(teamLocation);
        
        teamRepository.save(team);
        
        Player player = new Player();
        player.setName(playerName);
        player.setPno(number);
        Position pos = Position.fromPositionName(position);
        player.setPosition(pos);
        player.setTeam(team);
        playerRepository.save(player);
        //team.addPlayer(player);
	}
	
	public List<Player> getList(){
		List<Player> playerList = playerRepository.findAll();
		return playerList;
	}
}

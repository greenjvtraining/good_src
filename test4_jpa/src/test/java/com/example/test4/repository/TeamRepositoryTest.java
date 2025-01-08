package com.example.test4.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.test4.entity.Player;
import com.example.test4.entity.Team;

import jakarta.transaction.Transactional;
@Transactional
@SpringBootTest
class TeamRepositoryTest {

	@Autowired
	TeamRepository teamRepository;
	
	//@Test
	void testInsert() {
		
		Team teamA = Team.builder()
				.teamId("A")
				.teamName("Ajax")
				.build();
		
		Team result = teamRepository.save(teamA);
		System.out.println("result : " + result);
		
	}
	
	//@Test
	@Commit
	@Transactional
	void testInsert2() {
//		Team teamB = Team.builder()
//				.teamId("B")
//				.teamName("BlueScreen")
//				.build();
		//Team teamB = new Team("C", "Chicken");
		
		Optional<Team> opt = teamRepository.findById("B");
		Team teamB = opt.get();
		
		Player p1 = Player.builder()
				.name("Jeong")
				.role("attack")
				.build();
		
		Player p2 = Player.builder()
				.name("Shin")
				.role("defence")
				.build();
		
		teamB.addPlayer(p1);
		teamB.addPlayer(p2);
		
		//Team result = teamRepository.save(teamB);
		System.out.println("result : " + teamB);
	}
	
	//@Test
	void update() {
		Team team = new Team("Bleu Sharks", "Blue Giant");
		
		System.out.println(teamRepository.save(team));
	}
	
	//@Test
	void update2(){
		int result = teamRepository.updateTeamId("C", "CCC");
		System.out.println("result : " + result);
	}
	@Commit
	@Transactional
	//@Test
	void delete1() {
		Optional<Team> result = teamRepository.findById("B");
		if(result.isPresent()) {
			Team team = result.get();
			
			List<Player> players = team.getPlayers();
			team.removePlayer(players.get(0));
		}
		
		System.out.println("-----end----");
	}
	
	@Test
	void select1() {
		Optional<Team> result = teamRepository.findById("B");
		Team team = result.get();
		
		System.out.println("team : " + team);
	}
}

package com.example.test4.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.test4.dto.PlayerDto;
import com.example.test4.entity.Player;
import com.example.test4.entity.Team;

import jakarta.persistence.EntityManagerFactory;

@SpringBootTest
class PlayerRepositoryTest {

	@Autowired
	EntityManagerFactory emf;
	
	@Autowired
	PlayerRepository playerRepository;
	
	//@Test
	void testInsert() {
		
		Team team = Team.builder()
				.teamId("A")
				.build();
		
		Player p1 = Player.builder()
				.name("Son")
				.role("attack")
				.team(team)
				.build();
		
		Player result = playerRepository.save(p1);
		System.out.println("result : " + result);
	}
	
	//@Test
	void testReadAll() {
		
		List<Player> pList = playerRepository.findAll();
		
		for(Player p : pList) {
			System.out.println(p);
			System.out.println(p.getTeam().getTeamId());
		}
	}
	
	//@Test
	//@Transactional
	void testReadOne() {
		//Player p = playerRepository.findById(4).get();
		PlayerDto p = playerRepository.getPlayerWithTeamName(4);
		System.out.println(p);
	}
	
	//@Test
	//@Transactional
	void testReadAll2() {
		List<PlayerDto> list = playerRepository.getPlayerListWithTeamName();
		for(PlayerDto p : list) {
			System.out.println(p);
		}
	}
	
	//@Commit //붙여야 한다. 안그러면 db에 적용되지 않는다. application.properties에 autocommit 설정 시 생략가능
	//@Test
	//@Transactional
	void testUpdate() {
		//EntityManager em = emf.createEntityManager();
		//em.getTransaction().begin();
		
		int result = playerRepository.updatePlayer("아랑드롱4", "공격4수", "A", 4);
		System.out.println(result);
		
		//em.getTransaction().commit();
	}
	
	@Commit
	@Test
	@Transactional
	void testUpdate2() {
		Player p = playerRepository.findById(7).get();
		p.setName("Seungho3");
		p.setRole("coach3");
		Team team = new Team();
		team.setTeamId("D");
		p.setTeam(team);
		int result = playerRepository.updatePlayer2(7, p);
		System.out.println(result);
	}
	
	//@Test
	void testDelete() {
		int result = playerRepository.deletePlayer(4);
		System.out.println(result);
	}

}

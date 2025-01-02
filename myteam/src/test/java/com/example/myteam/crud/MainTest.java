package com.example.myteam.crud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.myteam.entity.Player;
import com.example.myteam.entity.Team;
import com.example.myteam.enums.Position;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@SpringBootTest
public class MainTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Test
	public void testRun() {
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		// 팀 생성
		Team team = new Team("Houston Rockets", "Houston");

		// 선수 생성
		Player olajuwon = new Player(34, "Hakeem Olajuwon", Position.CENTER);
		Player barkley = new Player(4, "Charles Barkley", Position.POWER_FORWARD);

		// 팀에 선수 추가
		team.addPlayer(olajuwon);
		team.addPlayer(barkley);

		// 팀 저장(CascadeType.ALL로 인해 선수도 저장됨)
		em.persist(team);

		em.getTransaction().commit();

		Team savedTeam = em.find(Team.class, team.getId());
		System.out.println("팀 이름 : " + savedTeam.getName());
		System.out.println("팀 연고지 : " + savedTeam.getHometown());
		for (Player p : savedTeam.getPlayers()) {
			System.out.println("선수 이름 : " + p.getName() + ", 포지션 : " + p.getPositionName());
		}

		em.close();
		entityManagerFactory.close();
	}
}

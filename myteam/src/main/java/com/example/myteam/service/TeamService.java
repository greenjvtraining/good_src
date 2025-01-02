package com.example.myteam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myteam.entity.Player;
import com.example.myteam.entity.Team;
import com.example.myteam.enums.Position;
import com.example.myteam.repository.TeamRepository;

@Service
@Transactional
public class TeamService {

	//@Autowired
	//private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private TeamRepository teamRepository;
	
	public void registTeam(String name, String hometown) {
		//EntityManager em = entityManagerFactory.createEntityManager();

		//em.getTransaction().begin();

		// 팀 생성
		Team team = new Team(name, hometown);
		teamRepository.save(team);

		// 선수 생성
		Player daugherty = new Player(43, "Brad Daugherty", Position.CENTER);
		Player price = new Player(25, "Mark Price", Position.POINT_GUARD);

		//System.out.println(1/0);
		// 팀에 선수 추가
		team.addPlayer(daugherty);
		team.addPlayer(price);
		

		// 팀 저장(CascadeType.ALL로 인해 선수도 저장됨)
		//em.persist(team);

		//em.getTransaction().commit();

		//Team savedTeam = em.find(Team.class, team.getId());
		//System.out.println("팀 이름 : " + savedTeam.getName());
		//System.out.println("팀 연고지 : " + savedTeam.getHometown());
		//for (Player p : savedTeam.getPlayers()) {
		//	System.out.println("선수 이름 : " + p.getName() + ", 포지션 : " + p.getPositionName());
		//}

		//em.close();
		//entityManagerFactory.close();
	}
	
	public List<Team> getList(){
		List<Team> teamList = teamRepository.findAll();
		
		return teamList;
	}
}

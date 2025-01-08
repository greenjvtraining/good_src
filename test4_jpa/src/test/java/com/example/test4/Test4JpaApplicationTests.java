package com.example.test4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.test4.entity.Team;
import com.example.test4.repository.TeamRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class Test4JpaApplicationTests {

	@Autowired
	TeamRepository teamRepository;
	
	@PersistenceContext
	EntityManager em;
	
	@Commit
	@Test
	void contextLoads() {
		Team team = new Team("D", "DDD");
		
		Team result = teamRepository.save(team);
		System.out.println(result);
		
		em.flush();
		//em.clear();
		
	}

}

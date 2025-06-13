package com.example.teamPlayer.team;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.teamPlayer.repository.TeamRepository;
import com.example.teamPlayer.service.TeamService;

@SpringBootTest
//@Transactional // 기본적으로 롤백되지만, false로 변경
public class TeamServiceTest {

	@Autowired
	TeamService teamService;
	@Autowired
	TeamRepository teamRepository;
	
	@Test
    @Rollback(false) // 이 테스트는 롤백되지 않고 커밋됩니다.
    void testInsertAndCommit() {
		teamService.regTeam();
		
    }
}

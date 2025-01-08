package com.example.test4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.test4.entity.Team;

import jakarta.transaction.Transactional;

public interface TeamRepository extends JpaRepository<Team, String>{
	
	@Transactional
	@Modifying
	@Query("UPDATE Team t SET t.teamId = :newId WHERE t.teamId = :oldId")
	int updateTeamId(@Param("oldId")String oldId, @Param("newId")String newId);

}

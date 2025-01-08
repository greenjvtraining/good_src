package com.example.test4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.test4.dto.PlayerDto;
import com.example.test4.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	// 조회
	@Query("SELECT new com.example.test4.dto.PlayerDto(p.pno, p.name, p.role, t.teamName) FROM Player p join p.team t WHERE p.pno = :pno")
	public PlayerDto getPlayerWithTeamName(@Param("pno") int pno);

	// 조회2
	@Query("SELECT new com.example.test4.dto.PlayerDto(p.pno, p.name, p.role, t.teamName) FROM Player p join p.team t")
	public List<PlayerDto> getPlayerListWithTeamName();

	// 등록 - save

	// 수정
	@Transactional
	@Modifying
	@Query("UPDATE Player p SET p.name = :name, p.role = :role, p.team.teamId = :teamId WHERE p.pno = :pno")
	public int updatePlayer(@Param("name") String name, @Param("role") String role, @Param("teamId") String teamId,	@Param("pno") Integer pno);

	// 수정
	@Transactional
	@Modifying
	@Query("UPDATE Player p SET p.name = :#{#player.name}, p.role = :#{#player.role}, p.team.teamId = :#{#player.team.teamId} WHERE p.pno = :pno")
	public int updatePlayer2(@Param("pno")Integer pno, @Param("player") Player player);

	@Transactional
	@Modifying
	@Query("DELETE FROM Player p WHERE p.pno = :pno")
	public int deletePlayer(@Param("pno") int pno);

}

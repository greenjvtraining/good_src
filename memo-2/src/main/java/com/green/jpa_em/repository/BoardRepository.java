package com.green.jpa_em.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.green.jpa_em.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	@Query("SELECT b. FROM Board b JOIN FETCH b.member")
	public List<Board> getBoardListWithMember();
}

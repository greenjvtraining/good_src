package com.example.simpleJpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simpleJpa.entity.BoardEntity;
import com.example.simpleJpa.entity.MemberEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
	
	List<BoardEntity> findByWriter(MemberEntity writer);
}

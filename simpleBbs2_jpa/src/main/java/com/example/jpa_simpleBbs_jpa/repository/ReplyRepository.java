package com.example.jpa_simpleBbs_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_simpleBbs_jpa.entity.Board;
import com.example.jpa_simpleBbs_jpa.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	public List<Reply> findByBoard(Board board);
}

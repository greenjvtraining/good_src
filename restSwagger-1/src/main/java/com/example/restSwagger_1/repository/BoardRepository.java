package com.example.restSwagger_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restSwagger_1.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}

package com.example.ai_ollama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ai_ollama.entity.BbsEntity;

public interface BbsRepository extends JpaRepository<BbsEntity, Integer>{

}

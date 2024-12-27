package com.example.simpleJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simpleJpa.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String>{
	MemberEntity findByUsername(String username);
}

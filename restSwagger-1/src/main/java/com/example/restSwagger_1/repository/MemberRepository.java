package com.example.restSwagger_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restSwagger_1.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	Member findByUsername(String username);
}

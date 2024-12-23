package com.example.myrest_secu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myrest_secu.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	public Member findByUsername(String username);
	public Boolean existsByUsername(String username);
}

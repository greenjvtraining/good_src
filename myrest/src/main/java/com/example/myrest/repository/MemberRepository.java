package com.example.myrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myrest.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	public Member findByUsernameAndPassword(String username, String password);
}

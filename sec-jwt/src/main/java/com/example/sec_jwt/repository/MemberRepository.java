package com.example.sec_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sec_jwt.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByUsername(String username);
	public Boolean existsByUsername(String username);

}

package com.example.secu2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.secu2.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByUsername(String username);
}

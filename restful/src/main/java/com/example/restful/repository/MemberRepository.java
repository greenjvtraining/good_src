package com.example.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restful.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	public Member findByUsername(String username);
}

package com.green.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.memo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

}

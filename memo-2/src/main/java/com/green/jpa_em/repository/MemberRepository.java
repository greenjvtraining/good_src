package com.green.jpa_em.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.jpa_em.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}

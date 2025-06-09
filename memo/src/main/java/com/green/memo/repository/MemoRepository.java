package com.green.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.green.memo.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer>{

	@Query("select m from Memo m join fetch m.member")
	List<Memo> findAllWithMember(); //Memo와 Memo.member가 하나의 쿼리로 함께 로드됨.
}

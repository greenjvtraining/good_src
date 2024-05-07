package com.example.jpaEx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpaEx.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>{
	//save(엔티티), findById(아이디), deleteById(아이디), findAll(), count()
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
}

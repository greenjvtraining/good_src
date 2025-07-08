package com.example.ai_pgv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ai_pgv.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	@Query(value = "SELECT * FROM items ORDER BY embedding <#> :vec LIMIT 1", nativeQuery = true)
	Item findMostSimilar(@Param("vec") float[] vec);
}

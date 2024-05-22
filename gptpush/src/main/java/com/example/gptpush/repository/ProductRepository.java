package com.example.gptpush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gptpush.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByOutOfStock(boolean outOfStock);
}

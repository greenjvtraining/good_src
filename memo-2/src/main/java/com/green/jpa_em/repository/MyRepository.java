package com.green.jpa_em.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MyRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

}

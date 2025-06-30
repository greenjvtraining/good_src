package com.green.rest_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.rest_basic.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}

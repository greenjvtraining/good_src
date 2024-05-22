package com.example.gptpush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gptpush.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

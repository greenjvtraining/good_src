package com.example.myteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myteam.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}

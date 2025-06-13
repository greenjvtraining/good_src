package com.example.teamPlayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teamPlayer.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}

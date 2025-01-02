package com.example.myteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myteam.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}

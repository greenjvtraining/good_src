package com.example.myteam.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_team")
@Getter
@Setter
public class Team {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false) 
    private String name;

    @Column(nullable = false) 
    private String hometown;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();
    
    // 기본 생성자
    public Team() {}

    // 생성자
    public Team(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }
    
    // 팀원 추가 메서드
    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this); // 양방향 연관관계 설정
    }

    // 팀원 제거 메서드
    public void removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null); // 연관관계 해제
    }
}

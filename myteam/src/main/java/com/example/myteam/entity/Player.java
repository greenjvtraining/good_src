package com.example.myteam.entity;

import com.example.myteam.enums.Position;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_player")
@Getter
@Setter
public class Player {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 ID
    private Long id;
	@Column(nullable = false)
	private Integer pno;
	@Column(nullable = false)
	private String name;
	@Enumerated(EnumType.STRING) // Enum 타입은 문자열로 저장
	@Column(nullable = false) 
	private Position position;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id") // 외래 키
    private Team team;
	
	// 기본 생성자
    public Player() {}

    // 생성자
    public Player(int pno, String name, Position position) {
        this.pno = pno;
        this.name = name;
        this.position = position;
    }
	
	// 포지션의 이름 반환
    public String getPositionName() {
        return position.getPositionName();
    }
    
    // 연관관계 메소드
    public void setTeam(Team team) {
        this.team = team;
        if (team != null && !team.getPlayers().contains(this)) {
            team.addPlayer(this); // Team 쪽에도 동기화
        }
    }
    
    
 // toString 메서드 재정의
    @Override
    public String toString() {
        return "Player{" +
                "pno=" + pno +
                ", name='" + name + '\'' +
                ", position=" + position.getPositionName() +
                '}';
    }
}

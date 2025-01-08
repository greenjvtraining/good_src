package com.example.test4.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_team")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Team {
	@Id
	private String teamId;
	private String teamName;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<Player> players = new ArrayList<>();
	
	// 연관관계 편의 메서드
    public void addPlayer(Player player) {
    	players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(Player player) {
    	players.remove(player);
        player.setTeam(null);
    }

    public Team(String teamId, String teamName) {
    	this.teamId = teamId;
    	this.teamName = teamName;
    }
}

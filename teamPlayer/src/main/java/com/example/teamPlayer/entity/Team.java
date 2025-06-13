package com.example.teamPlayer.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Team {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    private String name;

    @OneToMany(mappedBy = "team",
               cascade = CascadeType.PERSIST,   // 또는 ALL
               orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    /** 양방향 일관성 유지용 */
    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }
}

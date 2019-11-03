package com.jogos.ranking.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Winning implements Serializable {

    private static final long serialVersionUID = -4138887343530985008L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;

    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Player player_id;

    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Game game_id;

    @Column(nullable = false)
    private Integer scoreboard;
}

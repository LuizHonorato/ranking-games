package com.jogos.ranking.services;

import com.jogos.ranking.entities.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    Player save(Player player);

    List<Player> findAll();

    Optional<Player> findById(Long id);

    void deleteById(Long id);
}

package com.jogos.ranking.services;

import com.jogos.ranking.entities.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    Game save(Game game);

    List<Game> findAll();

    Optional<Game> findById(Long id);

    void deleteById(Long id);
}

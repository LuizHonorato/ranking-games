package com.jogos.ranking.services.impl;

import com.jogos.ranking.entities.Game;
import com.jogos.ranking.repositories.GameRepository;
import com.jogos.ranking.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository repository;

    @Override
    public Game save(Game game) { return repository.save(game); }

    @Override
    public List<Game> findAll() { return repository.findAll(); }

    @Override
    public Optional<Game> findById(Long id) { return repository.findById(id); }

    @Override
    public void deleteById(Long id) { repository.deleteById(id); }
}

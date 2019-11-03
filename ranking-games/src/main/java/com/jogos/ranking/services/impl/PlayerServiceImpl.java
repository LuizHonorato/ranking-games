package com.jogos.ranking.services.impl;

import com.jogos.ranking.entities.Player;
import com.jogos.ranking.repositories.PlayerRepository;
import com.jogos.ranking.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository repository;

    @Override
    public Player save(Player player) {
        return repository.save(player);
    }

    @Override
    public List<Player> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Player> ranking() { return repository.ranking(); }

    @Override
    public Optional<Player> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) { repository.deleteById(id); }

}

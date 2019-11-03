package com.jogos.ranking.services.impl;

import com.jogos.ranking.entities.Winning;
import com.jogos.ranking.repositories.WinningRepository;
import com.jogos.ranking.services.WinningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WinningServiceImpl implements WinningService {

    @Autowired
    WinningRepository repository;

    @Override
    public Winning save(Winning winning) { return repository.save(winning); }

    @Override
    public List<Winning> findAll() { return repository.findAll(); }

    @Override
    public Optional<Winning> findById(Long id) { return repository.findById(id); }

    @Override
    public List<Winning> ranking() { return repository.ranking(); }

    @Override
    public void deleteById(Long id) { repository.deleteById(id); }
}

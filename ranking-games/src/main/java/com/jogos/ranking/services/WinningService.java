package com.jogos.ranking.services;

import com.jogos.ranking.entities.Winning;

import java.util.List;
import java.util.Optional;

public interface WinningService {

    Winning save(Winning winning);

    List<Winning> findAll();

    Optional<Winning> findById(Long id);

    List<Winning> ranking();

    void deleteById(Long id);
}

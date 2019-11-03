package com.jogos.ranking.repositories;

import com.jogos.ranking.entities.Winning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinningRepository extends JpaRepository<Winning, Long> {

    @Query(nativeQuery = true, value = "SELECT w1.*, count(w2.scoreboard) as ranking FROM winning w1 JOIN winning w2 ON w1.scoreboard < w2.scoreboard OR (w1.scoreboard = w2.scoreboard) GROUP BY w1.scoreboard ORDER BY w1.scoreboard DESC LIMIT 3")
    List<Winning> ranking();
}

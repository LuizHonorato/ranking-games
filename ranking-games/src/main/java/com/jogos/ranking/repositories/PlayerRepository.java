package com.jogos.ranking.repositories;

import com.jogos.ranking.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(nativeQuery = true, value = "SELECT p1.*, count(p2.victories) as ranking FROM player p1 JOIN player p2 ON p1.victories < p2.victories OR (p1.victories = p2.victories) GROUP BY p1.victories ORDER BY p1.victories DESC LIMIT 3;")
    List<Player> ranking();
}

package com.jogos.ranking.repositories_test;

import com.jogos.ranking.entities.Game;
import com.jogos.ranking.repositories.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GameRepositoryTest {

    @Autowired
    GameRepository repository;

    @Test
    public void testSave() {
        Game game = new Game();
        game.setName("GameTest");

        Game response = repository.save(game);
        assertNotNull(response);
    }

}
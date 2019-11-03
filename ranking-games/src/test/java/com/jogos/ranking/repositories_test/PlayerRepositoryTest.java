package com.jogos.ranking.repositories_test;

import com.jogos.ranking.entities.Player;
import com.jogos.ranking.repositories.PlayerRepository;
import org.junit.Before;
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
public class PlayerRepositoryTest {

    @Autowired
    PlayerRepository repository;

    @Test
    public void testSave() {
        Player player = new Player();
        player.setName("UserTest");

        Player response = repository.save(player);

        assertNotNull(response);
    }
}

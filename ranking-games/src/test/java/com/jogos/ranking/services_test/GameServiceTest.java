package com.jogos.ranking.services_test;

import com.jogos.ranking.entities.Game;
import com.jogos.ranking.repositories.GameRepository;
import com.jogos.ranking.services.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GameServiceTest {

    private static final Long ID = 1L;

    @MockBean
    GameRepository repository;

    @Autowired
    GameService service;

    @Before
    public void setUp() {
        BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.of(new Game()));
    }

    @Test
    public void testFindById() {
        Optional<Game> game = service.findById(ID);

        assertNotNull(game);
    }

}

package com.jogos.ranking.services_test;

import com.jogos.ranking.entities.Player;
import com.jogos.ranking.repositories.PlayerRepository;
import com.jogos.ranking.services.PlayerService;
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

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PlayerServiceTest {

    private static final Long ID = 1L;

    @MockBean
    PlayerRepository repository;

    @Autowired
    PlayerService service;

    @Before
    public void setUp() {
        BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.of(new Player()));
    }

    @Test
    public void testFindById() {
        Optional<Player> player = service.findById(ID);

        assertNotNull(player);
    }
}

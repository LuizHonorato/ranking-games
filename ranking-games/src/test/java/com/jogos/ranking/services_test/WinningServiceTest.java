package com.jogos.ranking.services_test;

import com.jogos.ranking.entities.Winning;
import com.jogos.ranking.repositories.WinningRepository;
import com.jogos.ranking.services.WinningService;
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
public class WinningServiceTest {

    private static final Long ID = 1L;

    @MockBean
    WinningRepository repository;

    @Autowired
    WinningService service;

    @Before
    public void setUp() {
        BDDMockito.given(repository.findById(Mockito.anyLong())).willReturn(Optional.of(new Winning()));
    }

    @Test
    public void testFindById() {
        Optional<Winning> winning = service.findById(ID);

        assertNotNull(winning);
    }
}

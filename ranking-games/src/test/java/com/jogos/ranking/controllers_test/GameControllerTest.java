package com.jogos.ranking.controllers_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jogos.ranking.dto.GameDTO;
import com.jogos.ranking.entities.Game;
import com.jogos.ranking.services.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GameControllerTest {

    private static final Long ID = 1L;
    private static final String NAME = "GameName";
    private static final String URL = "/jogos";

    @MockBean
    GameService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void testSave() throws Exception {
        BDDMockito.given(service.save(Mockito.any(Game.class))).willReturn(getMockGame());

        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(NAME));
    }

    @Test
    public void testSaveInvalidGame() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "Ga"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Nome deve conter entre 3 e 20 caracteres"));
    }

    public Game getMockGame() {
        Game game = new Game();
        game.setId(ID);
        game.setName(NAME);

        return game;
    }

    public String getJsonPayload(Long id, String name) throws JsonProcessingException {
        GameDTO dto = new GameDTO();
        dto.setId(id);
        dto.setName(name);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

}

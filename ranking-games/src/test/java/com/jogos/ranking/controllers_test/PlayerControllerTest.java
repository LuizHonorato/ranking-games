package com.jogos.ranking.controllers_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jogos.ranking.dto.PlayerDTO;
import com.jogos.ranking.entities.Player;
import com.jogos.ranking.services.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
public class PlayerControllerTest {

    private static final Long ID = 1L;
    private static final String NAME = "PlayerName";
    private static final String URL = "/jogadores";

    @MockBean
    PlayerService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void testSave() throws Exception {
        BDDMockito.given(service.save(Mockito.any(Player.class))).willReturn(getMockPlayer());

        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(NAME));
    }

    @Test
    public void testSaveInvalidPlayer() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "Ex"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Nome deve conter entre 3 e 50 caracteres"));
    }

    public Player getMockPlayer() {
        Player player = new Player();
        player.setId(ID);
        player.setName(NAME);

        return player;
    }

    public String getJsonPayload(Long id, String name) throws JsonProcessingException {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(id);
        dto.setName(name);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }
}

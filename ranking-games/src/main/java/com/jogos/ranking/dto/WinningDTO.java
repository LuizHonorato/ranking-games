package com.jogos.ranking.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WinningDTO {

    private Long id;
    @NotNull(message = "Informe o jogador")
    private Long player_id;
    @NotNull(message = "Informe o jogo")
    private Long game_id;
    private Integer scoreboard;
}

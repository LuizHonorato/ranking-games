package com.jogos.ranking.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class GameDTO {

    private Long id;
    @Length(min=3, max=50, message = "Nome deve conter entre 3 e 20 caracteres")
    private String name;
}

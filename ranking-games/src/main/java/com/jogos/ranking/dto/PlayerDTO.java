package com.jogos.ranking.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PlayerDTO {

    private Long id;
    @Length(min=3, max=50, message = "Nome deve conter entre 3 e 50 caracteres")
    private String name;
    private Integer matches;
    private Integer victories;
}

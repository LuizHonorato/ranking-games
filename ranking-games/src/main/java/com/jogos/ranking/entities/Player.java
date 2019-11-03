package com.jogos.ranking.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 4440602808250996880L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String name;
}

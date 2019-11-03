package com.jogos.ranking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;

@Data
@Entity

public class Game implements Serializable {

    private static final long serialVersionUID = -9154934542021697125L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String name;
}

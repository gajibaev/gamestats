package com.gadjibaev.gamestats.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class GameStatistics  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "game_statistics_seq")
    @SequenceGenerator(name = "game_statistics_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "profileid", referencedColumnName = "id")
    private Profile profile;


    @JoinColumn(name = "gameid", referencedColumnName = "id")
    @OneToOne
    private Game game;

    private int hours;
}

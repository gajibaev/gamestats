package com.gadjibaev.gamestats.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_statistics")
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class GameStatistics  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_statistics_seq")
    @SequenceGenerator(name = "game_statistics_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "profileid", referencedColumnName = "id")
    @Nonnull
    private Profile profile;


    @JoinColumn(name = "gameid", referencedColumnName = "id")
    @OneToOne
    @Nonnull
    private Game game;

    private int hours;

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name=";
    }
}

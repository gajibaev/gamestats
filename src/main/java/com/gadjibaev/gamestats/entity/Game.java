package com.gadjibaev.gamestats.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Game{
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "games_seq")
   @SequenceGenerator(name = "games_seq", allocationSize = 1)
   @Column(name = "id")
   private Integer id;
   @Nonnull
   private String name;
   @Nonnull
   private String platform;
}

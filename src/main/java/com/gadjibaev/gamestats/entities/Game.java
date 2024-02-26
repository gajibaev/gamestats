package com.gadjibaev.gamestats.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Game{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "games_seq")
   @SequenceGenerator(name = "games_seq", allocationSize = 1)
   @Column(name = "id")
   private Integer id;

   private String name;

   private String platforms;
}

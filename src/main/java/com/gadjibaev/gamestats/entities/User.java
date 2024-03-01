package com.gadjibaev.gamestats.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    private String nickname;

    private Integer level;
}

package com.gadjibaev.gamestats.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "profiles_seq")
    @SequenceGenerator(name = "profiles_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    private String nickname;

    private Integer level;
}

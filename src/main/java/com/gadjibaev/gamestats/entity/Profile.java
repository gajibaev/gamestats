package com.gadjibaev.gamestats.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profiles_seq")
    @SequenceGenerator(name = "profiles_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NonNull
    private String nickname;

    private int level;
}

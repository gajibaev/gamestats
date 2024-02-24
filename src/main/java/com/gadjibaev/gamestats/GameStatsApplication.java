package com.gadjibaev.gamestats;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameStatsApplication implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(GameStatsApplication.class, args);
    }

    @Override
    public void run(String... args) {}
}

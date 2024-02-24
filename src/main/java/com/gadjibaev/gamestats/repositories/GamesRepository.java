package com.gadjibaev.gamestats.repositories;

import com.gadjibaev.gamestats.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface GamesRepository extends CrudRepository<Game, Integer> {
}

package com.gadjibaev.gamestats.repositories;

import com.gadjibaev.gamestats.entities.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface GamesRepository extends CrudRepository<Game, Integer> {
}

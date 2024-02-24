package com.gadjibaev.gamestats.repositories;

import com.gadjibaev.gamestats.entity.GameStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GameStatisticsRepository extends CrudRepository<GameStatistics, Integer> {
}

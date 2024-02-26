package com.gadjibaev.gamestats.repositories;

import com.gadjibaev.gamestats.entities.GameStatistics;
import org.springframework.data.repository.CrudRepository;

public interface GameStatisticsRepository extends CrudRepository<GameStatistics, Integer> {
}

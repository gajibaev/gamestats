package com.gadjibaev.gamestats.services;

import com.gadjibaev.gamestats.entities.Game;
import com.gadjibaev.gamestats.repositories.GamesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class GamesService {

    private final GamesRepository repository;

    public GamesService(GamesRepository repository){
        this.repository = repository;
    }

    public Iterable<Game> getGames(){
        return repository.findAll();
    }

    public Iterable<Game> getGamesByIds(List<Integer> ids){
        return repository.findAllById(ids);
    }

    public Game getGameById(int id) throws Exception {
        return repository.findById(id)
                .orElseThrow(Exception::new);
    }

    public Game saveGame(Game body) throws Exception{
        if (body.getName() != null && body.getPlatforms() != null) {
            final var game = repository.save(body);

            log.info("Created game: {}", body.getName());

            return game;
        } else {
            throw new Exception("'name' or 'platform' field is empty");
        }
    }

    public Iterable<Game> saveGames(List<Game> bodies){
        final var games = repository.saveAll(bodies);

        log.info("Created games: {}", bodies.stream().map(Game::getName));

        return games;
    }

    public void deleteGame(int id) {
        repository.deleteById(id);
    }

    public Game updateGame(int id, Game body) throws Exception {
        Game existingGame = repository.findById(id)
                .orElseThrow(Exception::new);

        if (body.getName() != null) {
            existingGame.setName(body.getName());
        }
        if(body.getPlatforms() != null) {
            existingGame.setPlatforms(body.getPlatforms());
        }

        return repository.save(existingGame);
    }
}

package com.gadjibaev.gamestats.services;

import com.gadjibaev.gamestats.entities.Game;
import com.gadjibaev.gamestats.entities.GameStatistics;
import com.gadjibaev.gamestats.entities.Profile;
import com.gadjibaev.gamestats.models.GameStatisticsPostBody;
import com.gadjibaev.gamestats.models.GameStatisticsSpendTimePostBody;
import com.gadjibaev.gamestats.repositories.GameStatisticsRepository;
import com.gadjibaev.gamestats.repositories.GamesRepository;
import com.gadjibaev.gamestats.repositories.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GameStatisticsService {

    private final GameStatisticsRepository gameStatisticsRepository;

    private final GamesRepository gamesRepository;

    private final ProfilesRepository profilesRepository;

    private final ProfilesService profilesService;

    public GameStatisticsService(GameStatisticsRepository gameStatisticsRepository, GamesRepository gamesRepository, ProfilesRepository profilesRepository, ProfilesService profilesService){
        this.gameStatisticsRepository = gameStatisticsRepository;
        this.gamesRepository = gamesRepository;
        this.profilesRepository = profilesRepository;
        this.profilesService = profilesService;
    }

    public Iterable<GameStatistics> getGameStatistics(){
        return gameStatisticsRepository.findAll();
    }

    public Iterable<GameStatistics> getGameStatisticsByIds(List<Integer> ids){
        return gameStatisticsRepository.findAllById(ids);
    }

    public GameStatistics getGameStatisticsById(int id) throws Exception {
        return gameStatisticsRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    public GameStatistics saveGameStatistics(GameStatisticsPostBody body) throws Exception{
        if (body.gameId().isPresent() && body.profileId().isPresent()) {
            final Game existingGame = gamesRepository.findById(body.gameId().get()).orElseThrow(Exception::new);
            final Profile existingProfile = profilesRepository.findById(body.profileId().get()).orElseThrow(Exception::new);

            final GameStatistics gameStatistics = new GameStatistics(null, existingProfile, existingGame, 1);

            return gameStatisticsRepository.save(gameStatistics);
        } else {
            throw new Exception("'nickname' or 'level' field is empty");
        }
    }

    public Iterable<GameStatistics> saveGameStatistics(List<GameStatisticsPostBody> bodies) throws  Exception{
        final ArrayList<GameStatistics> gameStatistics = new ArrayList<>();

        for (final GameStatisticsPostBody body : bodies) {
            if (body.gameId().isPresent() && body.profileId().isPresent()) {
                final Game existingGame = gamesRepository.findById(body.gameId().get()).orElseThrow(Exception::new);
                final Profile existingProfile = profilesRepository.findById(body.profileId().get()).orElseThrow(Exception::new);

                final GameStatistics createdGameStatistics = new GameStatistics(null, existingProfile, existingGame, 1);

                gameStatistics.add(createdGameStatistics);
            } else {
                throw new Exception("'nickname' or 'level' field is empty");
            }
        }

        return gameStatisticsRepository.saveAll(gameStatistics);
    }

    public String deleteGameStatistics(int id) {
        gameStatisticsRepository.deleteById(id);
        return "game statistics with id: " + id + " removed";
    }

    public void incrementGameSpendTime(GameStatisticsSpendTimePostBody body) throws Exception {
        final GameStatistics existingGameStatistics = gameStatisticsRepository.findById(body.id())
                .orElseThrow(Exception::new);

        final Profile existingProfile = profilesRepository.findById(existingGameStatistics.getProfile().getId())
                .orElseThrow(Exception::new);

        existingGameStatistics.setHours(existingGameStatistics.getHours() + body.hours());

        gameStatisticsRepository.save(existingGameStatistics);

        final int currentLevel = existingProfile.getLevel();
        final int currentGameSpendTime = existingGameStatistics.getHours();

        final int actualLevel = currentGameSpendTime / 10;

        if(actualLevel != currentLevel){
            profilesService.incrementLevelById(existingProfile.getId(), actualLevel - currentLevel);
        }
    }

    public GameStatistics updateGameStatistics(int id, GameStatisticsPostBody body) throws Exception {
        GameStatistics existingGameStatistics = gameStatisticsRepository.findById(id)
                .orElseThrow(Exception::new);

        if(body.gameId().isPresent()) {
            final Game existingGame = gamesRepository.findById(body.gameId().get()).orElseThrow(Exception::new);
            existingGameStatistics.setGame(existingGame);
        }

        if(body.profileId().isPresent()) {
            final Profile existingProfile = profilesRepository.findById(body.gameId().get()).orElseThrow(Exception::new);
            existingGameStatistics.setProfile(existingProfile);
        }

        if(body.hours().isPresent()){
            existingGameStatistics.setHours(body.hours().get());
        }

        return gameStatisticsRepository.save(existingGameStatistics);
    }
}

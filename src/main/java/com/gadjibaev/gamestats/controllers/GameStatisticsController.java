package com.gadjibaev.gamestats.controllers;

import com.gadjibaev.gamestats.entities.GameStatistics;
import com.gadjibaev.gamestats.models.GameStatisticsPostBody;
import com.gadjibaev.gamestats.models.GameStatisticsSpendTimePostBody;
import com.gadjibaev.gamestats.services.GameStatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/statistics", produces="application/json")
@CrossOrigin(origins="*")
public class GameStatisticsController {

    private final GameStatisticsService gameStatisticsService;

    public GameStatisticsController(GameStatisticsService gameStatisticsService){
        this.gameStatisticsService = gameStatisticsService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<GameStatistics>> getAll() {
        return new ResponseEntity<>(gameStatisticsService.getGameStatistics(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<Object> create(@RequestBody GameStatisticsPostBody body) {
        try {
            return new ResponseEntity<>(gameStatisticsService.saveGameStatistics(body), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/increment_spend_time", consumes = "application/json")
    ResponseEntity<Object> incrementSpendTime(@RequestBody GameStatisticsSpendTimePostBody body) {
        try {
            gameStatisticsService.incrementGameSpendTime(body);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getById(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(gameStatisticsService.getGameStatisticsById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@RequestBody GameStatisticsPostBody body, @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(gameStatisticsService.updateGameStatistics(id, body), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        gameStatisticsService.deleteGameStatistics(id);
    }
}


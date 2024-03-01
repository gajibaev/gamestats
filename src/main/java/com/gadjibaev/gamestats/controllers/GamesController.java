package com.gadjibaev.gamestats.controllers;

import com.gadjibaev.gamestats.entities.Game;
import com.gadjibaev.gamestats.services.GamesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/games", produces="application/json")
@CrossOrigin(origins="*")
public class GamesController {

    private final GamesService gamesService;

    public GamesController(GamesService gamesService){
        this.gamesService = gamesService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Game>> getAll() {
        return new ResponseEntity<>(gamesService.getGames(), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<Object> create(@RequestBody Game body) {
        try {
            return new ResponseEntity<>(gamesService.saveGame(body), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getById(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(gamesService.getGameById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@RequestBody Game body, @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(gamesService.updateGame(id, body), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        gamesService.deleteGame(id);
    }
}

package com.gadjibaev.gamestats.controllers;

import com.gadjibaev.gamestats.entities.User;
import com.gadjibaev.gamestats.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/users", produces="application/json")
@CrossOrigin(origins="*")
public class UsersController {

    private final UsersService usersService;
    
    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }
    
    @GetMapping("/")
    public ResponseEntity<Iterable<User>> getAll() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/")
    ResponseEntity<Object> create(@RequestBody User body) {
        try {
            return new ResponseEntity<>(usersService.saveUser(body), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getById(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(usersService.getUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@RequestBody User body, @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(usersService.updateUser(id, body), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    void delete( @PathVariable Integer id) {
        usersService.deleteUser(id);
    }
}

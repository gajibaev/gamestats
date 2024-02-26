package com.gadjibaev.gamestats.controllers;

import com.gadjibaev.gamestats.entities.Profile;
import com.gadjibaev.gamestats.services.ProfilesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/profiles", produces="application/json")
@CrossOrigin(origins="*")
public class ProfilesController {

    private final ProfilesService profilesService;
    
    public ProfilesController(ProfilesService profilesService){
        this.profilesService = profilesService;
    }
    
    @GetMapping()
    public ResponseEntity<Iterable<Profile>> getAll() {
        return new ResponseEntity<>(profilesService.getProfiles(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<Object> create(@RequestBody Profile body) {
        try {
            return new ResponseEntity<>(profilesService.saveProfile(body), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getById(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(profilesService.getProfileById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> update(@RequestBody Profile body, @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(profilesService.updateProfile(id, body), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    void delete( @PathVariable Integer id) {
        profilesService.deleteProfile(id);
    }
}

package com.gadjibaev.gamestats.services;

import com.gadjibaev.gamestats.entities.Profile;
import com.gadjibaev.gamestats.repositories.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfilesService {

    private final ProfilesRepository repository;

    public ProfilesService(ProfilesRepository repository){
        this.repository = repository;
    }

    public Iterable<Profile> getProfiles(){
        return repository.findAll();
    }

    public Iterable<Profile> getProfilesByIds(List<Integer> ids){
        return repository.findAllById(ids);
    }

    public Profile getProfileById(int id) throws Exception {
        return repository.findById(id)
                .orElseThrow(Exception::new);
    }

    public Profile saveProfile(Profile body) throws Exception{
        if (body.getNickname() != null && body.getLevel() != null) {
            return repository.save(body);
        } else {
            throw new Exception("'nickname' or 'level' field is empty");
        }
    }

    public Iterable<Profile> saveProfiles(List<Profile> bodies){
        return repository.saveAll(bodies);
    }

    public String deleteProfile(int id) {
        repository.deleteById(id);
        return "profile with id: " + id + " removed";
    }

    public Profile incrementLevelById(int id, int increment) throws Exception{
        Profile existingProfile = repository.findById(id)
                .orElseThrow(Exception::new);

        final int currentLevel = existingProfile.getLevel();

        existingProfile.setLevel(currentLevel + increment);

        return repository.save(existingProfile);
    }

    public Profile updateProfile(int id, Profile body) throws Exception {
        Profile existingProfile = repository.findById(id)
                .orElseThrow(Exception::new);

        if(body.getNickname() != null) {
            existingProfile.setNickname(body.getNickname());
        }

        if(body.getLevel() != null) {
            existingProfile.setLevel(body.getLevel());
        }

        return repository.save(existingProfile);
    }
}

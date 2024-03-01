package com.gadjibaev.gamestats.services;

import com.gadjibaev.gamestats.entities.User;
import com.gadjibaev.gamestats.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersService {

    private final UsersRepository repository;

    public UsersService(UsersRepository repository){
        this.repository = repository;
    }

    public Iterable<User> getUsers(){
        return repository.findAll();
    }

    public Iterable<User> getUsersByIds(List<Integer> ids){
        return repository.findAllById(ids);
    }

    public User getUserById(int id) throws Exception {
        return repository.findById(id)
                .orElseThrow(Exception::new);
    }

    public User saveUser(User body) throws Exception{
        if (body.getNickname() != null && body.getLevel() != null) {
            return repository.save(body);
        } else {
            throw new Exception("'nickname' or 'level' field is empty");
        }
    }

    public Iterable<User> saveUsers(List<User> bodies){
        return repository.saveAll(bodies);
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "profile with id: " + id + " removed";
    }

    public User incrementLevelById(int id, int increment) throws Exception{
        User existingUser = repository.findById(id)
                .orElseThrow(Exception::new);

        final int currentLevel = existingUser.getLevel();

        existingUser.setLevel(currentLevel + increment);

        return repository.save(existingUser);
    }

    public User updateUser(int id, User body) throws Exception {
        User existingUser = repository.findById(id)
                .orElseThrow(Exception::new);

        if(body.getNickname() != null) {
            existingUser.setNickname(body.getNickname());
        }

        if(body.getLevel() != null) {
            existingUser.setLevel(body.getLevel());
        }

        return repository.save(existingUser);
    }
}

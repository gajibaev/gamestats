package com.gadjibaev.gamestats.services;

import com.gadjibaev.gamestats.entities.User;
import com.gadjibaev.gamestats.repositories.UsersRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UsersService {

    private final UsersRepository repository;

    private final Gauge usersCountGauge;

    public UsersService(UsersRepository repository, MeterRegistry meterRegistry){
        this.repository = repository;

        usersCountGauge =
                Gauge.builder("users.count", this, UsersService::getUsersCount)
                        .description("Count of users")
                        .register(meterRegistry);
    }

    public Long getUsersCount(){
        return repository.count();
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
            final var user = repository.save(body);

            log.info("Created user: {}", body.getNickname());

            usersCountGauge.measure();

            return user;
        } else {
            throw new Exception("'nickname' or 'level' field is empty");
        }
    }

    public Iterable<User> saveUsers(List<User> bodies){;
        final var users = repository.saveAll(bodies);

        log.info("Created users: {}", users.stream().map(User::getNickname));

        usersCountGauge.measure();

        return users;
    }

    public void deleteUser(int id) {
        repository.deleteById(id);

        usersCountGauge.measure();
    }

    public void incrementLevelById(int id, int increment) throws Exception{
        User existingUser = repository.findById(id)
                .orElseThrow(Exception::new);

        final int currentLevel = existingUser.getLevel();

        existingUser.setLevel(currentLevel + increment);

        repository.save(existingUser);

        log.info("{} levels were added to the user with id: {}", increment, id);
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

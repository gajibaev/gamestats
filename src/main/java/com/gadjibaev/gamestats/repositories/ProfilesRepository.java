package com.gadjibaev.gamestats.repositories;

import com.gadjibaev.gamestats.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepository extends CrudRepository<Profile, Integer> {
}

package com.gadjibaev.gamestats.repositories;

import com.gadjibaev.gamestats.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepository extends CrudRepository<Profile, Integer> {
}

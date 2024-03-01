package com.gadjibaev.gamestats.repositories;

import com.gadjibaev.gamestats.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByNickname(String username);
}

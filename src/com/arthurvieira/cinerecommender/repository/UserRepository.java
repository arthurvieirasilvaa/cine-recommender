package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    void deleteById(long id);
    User findById(long id);
    List<User> listAll();
}
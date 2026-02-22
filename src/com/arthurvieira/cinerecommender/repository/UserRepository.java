package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.User;

public interface UserRepository {
    User save(User user);
    void delete(User user);
    long generateId();
}

package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email) {
        User user = new User(0, name, email);

        return this.userRepository.save(user);
    }

    public List<User> listUsers() {
        return this.userRepository.listAll();
    }
}
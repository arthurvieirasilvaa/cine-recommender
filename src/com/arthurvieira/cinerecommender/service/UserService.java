package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.repository.UserRepository;
import com.arthurvieira.cinerecommender.util.ValidationUtils;

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

    public User filterUserById(long id) {
        ValidationUtils.validateId(id);
        return this.userRepository.findById(id);
    }

    public User deleteUser(long id) {
        ValidationUtils.validateId(id);
        return this.userRepository.deleteById(id);
    }
}
package com.arthurvieira.cinerecommender.exception;

public class UserWithNoRatings extends RuntimeException {
    public UserWithNoRatings(String message) {
        super(message);
    }
}

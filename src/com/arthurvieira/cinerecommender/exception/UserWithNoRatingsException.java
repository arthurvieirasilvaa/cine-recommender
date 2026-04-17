package com.arthurvieira.cinerecommender.exception;

public class UserWithNoRatingsException extends RuntimeException {
    public UserWithNoRatingsException(String message) {
        super(message);
    }
}

package com.arthurvieira.cinerecommender.domain;

public enum Genre {
    ACTION("Action"),
    DRAMA("Drama"),
    COMEDY("Comedy"),
    HORROR("Horror"),
    SCI_FI("Sci-fi"),
    ROMANCE("Romance"),
    THRILLER("Thriller"),
    DOCUMENTARY("Documentary");

    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
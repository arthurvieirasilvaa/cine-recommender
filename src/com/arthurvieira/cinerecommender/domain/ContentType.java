package com.arthurvieira.cinerecommender.domain;

public enum ContentType {
    MOVIE("Movie"),
    SERIES("Series");

    private final String description;

    ContentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
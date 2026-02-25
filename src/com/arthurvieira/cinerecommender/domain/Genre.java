package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.exception.InvalidGenreException;

public enum Genre {
    ACTION("Action"),
    DRAMA("Drama"),
    COMEDY("Comedy"),
    HORROR("Horror"),
    SCI_FI("Sci-fi"),
    ROMANCE("Romance"),
    THRILLER("Thriller"),
    DOCUMENTARY("Documentary");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public static Genre fromOption(int option) {
        Genre[] genreValues = Genre.values();

        if(option < 1 || option > genreValues.length) {
            throw new InvalidGenreException("Opção inválida para o gênero!");
        }

        return genreValues[option-1];
    }

    public String getName() {
        return name;
    }
}
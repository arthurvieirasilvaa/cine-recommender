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

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public static Genre genreFromName(String text) {
        for(Genre genre : Genre.values()) {
            if(genre.getName().equals(text)) {
                return genre;
            }
        }

        throw new IllegalArgumentException("Ivalid genre: "+text);
    }

    public String getName() {
        return name;
    }
}
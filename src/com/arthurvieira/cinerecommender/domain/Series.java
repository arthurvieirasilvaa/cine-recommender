package com.arthurvieira.cinerecommender.domain;

import java.time.Year;

public class Series extends Content {
    private final int numberOfSeasons;
    private final int totalEpisodes;

    public Series(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, int numberOfSeasons, int totalEpisodes) {
        super(id, title, releaseYear, genre, ageRating, ContentType.SERIES);

        if(numberOfSeasons <= 0) {
            throw new IllegalArgumentException("Number of seasons must be positive!");
        }

        if(totalEpisodes <= 0) {
            throw new IllegalArgumentException("Total episodes must be positive!");
        }

        this.numberOfSeasons = numberOfSeasons;
        this.totalEpisodes = totalEpisodes;
    }

    @Override
    public String toString() {
        return "Series{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", releaseYear=" + getReleaseYear() +
                ", genre=" + getGenre() +
                ", ageRating=" + getAgeRating() +
                ", contentType=" + getContentType() +
                ", ratings=" + getRatings() +
                ", numberOfSeasons=" + numberOfSeasons +
                ", totalEpisodes=" + totalEpisodes +
                '}';
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }
}
package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.exception.InvalidNumberOfSeasonsException;
import com.arthurvieira.cinerecommender.exception.InvalidTotalEpisodesException;

import java.time.Year;

public class Series extends Content {
    private final int numberOfSeasons;
    private final int totalEpisodes;

    public Series(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, int numberOfSeasons, int totalEpisodes) {
        super(id, title, releaseYear, genre, ageRating, ContentType.SERIES);

        // Validate number of seasons:
        if(numberOfSeasons <= 0) {
            throw new InvalidNumberOfSeasonsException("O número de temporadas deve ser positivo!");
        }

        this.numberOfSeasons = numberOfSeasons;

        // Validate total episodes:
        if(totalEpisodes <= 0) {
            throw new InvalidTotalEpisodesException("O total de episódios deve ser positivo!");
        }

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
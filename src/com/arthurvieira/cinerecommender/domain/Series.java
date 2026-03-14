package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.util.ValidationUtils;

import java.time.Year;

public class Series extends Content {
    private final int numberOfSeasons;
    private final int totalEpisodes;

    public Series(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, int numberOfSeasons, int totalEpisodes) {
        super(id, title, releaseYear, genre, ageRating, ContentType.SERIES);

        ValidationUtils.validateSeriesNumberOfSeasons(numberOfSeasons);
        this.numberOfSeasons = numberOfSeasons;

        ValidationUtils.validateSeriesTotalEpisodes(totalEpisodes);
        this.totalEpisodes = totalEpisodes;
    }

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                ", ageRating=" + ageRating +
                ", contentType=" + contentType +
                ", ratings=" + ratings +
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

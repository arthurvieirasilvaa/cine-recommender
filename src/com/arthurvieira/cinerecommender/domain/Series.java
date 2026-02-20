package com.arthurvieira.cinerecommender.domain;

import java.time.LocalDate;
import java.util.List;

public class Series extends Content {
    private int numberOfSeasons;
    private int totalEpisodes;

    public Series(long id, String title, LocalDate releaseYear, ContentType contentType, int numberOfSeasons, int totalEpisodes) {
        super(id, title, releaseYear, contentType);
        this.numberOfSeasons = numberOfSeasons;
        this.totalEpisodes = totalEpisodes;
    }

    public Series(long id, String title, LocalDate releaseYear, Genre genre, ContentType contentType, int numberOfSeasons, int totalEpisodes) {
        super(id, title, releaseYear, genre, contentType);
        this.numberOfSeasons = numberOfSeasons;
        this.totalEpisodes = totalEpisodes;
    }

    public Series(long id, String title, LocalDate releaseYear, ContentType contentType, Genre genre, AgeRating ageRating, int numberOfSeasons, int totalEpisodes) {
        super(id, title, releaseYear, contentType, genre, ageRating);
        this.numberOfSeasons = numberOfSeasons;
        this.totalEpisodes = totalEpisodes;
    }

    public Series(long id, String title, LocalDate releaseYear, Genre genre, AgeRating ageRating, ContentType contentType, List<Rating> ratings, int numberOfSeasons, int totalEpisodes) {
        super(id, title, releaseYear, genre, ageRating, contentType, ratings);
        this.numberOfSeasons = numberOfSeasons;
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

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }
}
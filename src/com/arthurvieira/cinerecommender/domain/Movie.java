package com.arthurvieira.cinerecommender.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Movie extends Content {
    private LocalTime durationMinutes;

    public Movie(long id, String title, LocalDate releaseYear, ContentType contentType, LocalTime durationMinutes) {
        super(id, title, releaseYear, contentType);
        this.durationMinutes = durationMinutes;
    }

    public Movie(long id, String title, LocalDate releaseYear, Genre genre, ContentType contentType, LocalTime durationMinutes) {
        super(id, title, releaseYear, genre, contentType);
        this.durationMinutes = durationMinutes;
    }

    public Movie(long id, String title, LocalDate releaseYear, ContentType contentType, Genre genre, AgeRating ageRating, LocalTime durationMinutes) {
        super(id, title, releaseYear, contentType, genre, ageRating);
        this.durationMinutes = durationMinutes;
    }

    public Movie(long id, String title, LocalDate releaseYear, Genre genre, AgeRating ageRating, ContentType contentType, List<Rating> ratings, LocalTime durationMinutes) {
        super(id, title, releaseYear, genre, ageRating, contentType, ratings);
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                ", ageRating=" + ageRating +
                ", contentType=" + contentType +
                ", ratings=" + ratings +
                ", durationMinutes=" + durationMinutes +
                '}';
    }

    public LocalTime getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(LocalTime durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}

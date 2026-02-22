package com.arthurvieira.cinerecommender.domain;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Year;

public class Movie extends Content {
    private final Duration duration;

    public Movie(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, Duration duration) {
        super(id, title, releaseYear, genre, ageRating, ContentType.MOVIE);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", releaseYear=" + getReleaseYear() +
                ", genre=" + getGenre() +
                ", ageRating=" + getAgeRating() +
                ", contentType=" + getContentType() +
                ", ratings=" + getRatings() +
                ", durationMinutes=" + getDuration().toMinutes() +
                '}';
    }

    public Duration getDuration() {
        return duration;
    }
}

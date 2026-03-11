package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.util.ValidationUtils;

import java.time.Duration;
import java.time.Year;

public class Movie extends Content {
    private final Duration duration;

    public Movie(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, Duration duration) {
        super(id, title, releaseYear, genre, ageRating, ContentType.MOVIE);

        ValidationUtils.validateMovieDuration(duration);
        this.duration = duration;
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
                ", duration=" + duration +
                '}';
    }

    public Duration getDuration() {
        return duration;
    }
}
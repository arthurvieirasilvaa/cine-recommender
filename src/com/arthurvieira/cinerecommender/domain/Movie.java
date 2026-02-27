package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.exception.InvalidDurationException;

import java.time.Duration;
import java.time.Year;

public class Movie extends Content {
    private final Duration duration;

    public Movie(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, Duration duration) {
        super(id, title, releaseYear, genre, ageRating, ContentType.MOVIE);

        // Validate duration:
        if(duration.isNegative() || duration.isZero()) {
            throw new InvalidDurationException("A duração do filme deve ser positiva!");
        }

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

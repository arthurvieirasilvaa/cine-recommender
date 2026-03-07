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
        return "\t-> Filme " + getTitle() + " com ID " + getId() + ":\n" +
               "\t\t- Ano de lançamento: " + getReleaseYear() + "\n" +
                "\t\t- Gênero: " + getGenre().getName() + "\n" +
                "\t\t- Classificação Indicativa: " + getAgeRating().getDescription() + "\n" +
                "\t\t- Duração: " + getDuration().toMinutes() + " minutos\n";
    }

    public Duration getDuration() {
        return duration;
    }
}
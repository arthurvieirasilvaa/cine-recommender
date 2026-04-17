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

    @Override
    public String formatContent() {
        return "\t-> Filme "+this.getTitle()+" com ID "+this.getId()+":\n"+
                "\t\t- Ano de lançamento: "+this.getReleaseYear()+"\n"+
                "\t\t- Gênero: "+this.getGenre().getName()+"\n"+
                "\t\t- Classificação Indicativa: "+this.getAgeRating().getDescription()+"\n"+
                "\t\t- Duração: "+this.getDuration().toMinutes()+" minutos\n";
    }

    @Override
    public String formatSummary() {
        return "\t\t - Filme: "+this.getTitle();
    }

    public Duration getDuration() {
        return duration;
    }
}

package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.AgeRating;
import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.Genre;
import com.arthurvieira.cinerecommender.domain.Movie;
import com.arthurvieira.cinerecommender.exception.InvalidDurationException;
import com.arthurvieira.cinerecommender.exception.InvalidIdException;
import com.arthurvieira.cinerecommender.exception.InvalidReleaseYearException;

import java.time.Duration;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ContentService {
    List<Content> contents = new ArrayList<>();

    public void validateId(long id) {
        if(id <= 0) {
            throw new InvalidIdException("O ID do conteúdo deve ser positivo!");
        }

        for(Content content : contents) {
            if(content.getId() == id) {
                throw new InvalidIdException("Já existe um conteúdo com o ID fornecido!");
            }
        }
    }

    public void validateReleaseYear(Year releaseYear) {
        if(releaseYear.getValue() <= Year.MIN_VALUE || releaseYear.getValue() >= Year.MAX_VALUE) {
            throw new InvalidReleaseYearException("O ano de lançamento informado está inválido!");
        }
    }

    public void validateDuration(Duration duration) {
        if(duration.isNegative() || duration.isZero()) {
            throw new InvalidDurationException("A duração do filme deve ser positiva!");
        }
    }

    public Movie createMovie(String title, Year releaseYear, Genre genre, AgeRating ageRating, Duration duration) {
        // generate id
        // validar id

        Movie movie = new Movie(id, title, releaseYear, genre, ageRating);
        contents.add(movie);
        return movie;
    }
}

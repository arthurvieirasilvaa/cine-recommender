package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.AgeRating;
import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.Genre;
import com.arthurvieira.cinerecommender.domain.Movie;
import com.arthurvieira.cinerecommender.exception.InvalidDurationException;
import com.arthurvieira.cinerecommender.exception.InvalidIdException;
import com.arthurvieira.cinerecommender.exception.InvalidReleaseYearException;
import com.arthurvieira.cinerecommender.repository.ContentRepository;

import java.time.Duration;
import java.time.Year;

public class ContentService {
    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    private void validateId(long id) {
        if(id <= 0) {
            throw new InvalidIdException("O ID do conteúdo deve ser positivo!");
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
        validateReleaseYear(releaseYear);
        validateDuration(duration);

        Movie movie = new Movie(0, title, releaseYear, genre, ageRating, duration);

        return (Movie) this.contentRepository.save(movie);
    }

    public void deleteContent(long id) {
        validateId(id);
        this.contentRepository.deleteById(id);
    }

    public Content searchContent(long id) {
        validateId(id);
        return this.contentRepository.findById(id);
    }
}
package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.*;
import com.arthurvieira.cinerecommender.exception.InvalidIdException;
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

    public Movie createMovie(String title, Year releaseYear, Genre genre,
                             AgeRating ageRating, Duration duration) {
        Movie movie = new Movie(0, title, releaseYear, genre, ageRating, duration);

        return (Movie) this.contentRepository.save(movie);
    }

    public Series createSeries(String title, Year releaseYear, Genre genre,
                               AgeRating ageRating, int numberOfSeasons,
                               int totalEpisodes) {
        Series series = new Series(0, title, releaseYear, genre, ageRating, numberOfSeasons, totalEpisodes);

        return (Series) this.contentRepository.save(series);
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

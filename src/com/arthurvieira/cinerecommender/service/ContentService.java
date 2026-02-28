package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.*;
import com.arthurvieira.cinerecommender.exception.InvalidIdException;
import com.arthurvieira.cinerecommender.repository.ContentRepository;

import java.time.Duration;
import java.time.Year;
import java.util.List;

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

    public List<Content> listAll() {
        return this.contentRepository.listAll();
    }

    public void deleteContent(long id) {
        validateId(id);
        this.contentRepository.deleteById(id);
    }

    public Content filterContentById(long id) {
        validateId(id);
        return this.contentRepository.findById(id);
    }

    public List<Content> filterContentsByGenre(Genre genre) {
        return this.contentRepository.findByGenre(genre);
    }

    public List<Content> filterContentsByContentType(ContentType contentType) {
        return this.contentRepository.findByType(contentType);
    }
}
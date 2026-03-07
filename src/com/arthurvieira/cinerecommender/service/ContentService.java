package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.*;
import com.arthurvieira.cinerecommender.repository.ContentRepository;
import com.arthurvieira.cinerecommender.util.ValidationUtils;

import java.time.Duration;
import java.time.Year;
import java.util.List;

public class ContentService {
    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
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

    public Content filterContentById(long id) {
        ValidationUtils.validateId(id);
        return this.contentRepository.findById(id);
    }

    public Content deleteContent(long id) {
        ValidationUtils.validateId(id);
        return this.contentRepository.deleteById(id);
    }

    public List<Content> filterContentsByGenre(Genre genre) {
        return this.contentRepository.findByGenre(genre);
    }

    public List<Content> filterContentsByContentType(ContentType contentType) {
        return this.contentRepository.findByType(contentType);
    }

    public List<Content> filterContentsByAgeRating(AgeRating ageRating) {
        return this.contentRepository.findByAgeRating(ageRating);
    }
}
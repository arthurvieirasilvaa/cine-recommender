package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.AgeRating;
import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.ContentType;
import com.arthurvieira.cinerecommender.domain.Genre;

import java.util.List;

public interface ContentRepository {
    Content save(Content content);
    void delete(Content content);
    long generateId();
    List<Content> listByGenre(Genre genre);
    List<Content> listByType(ContentType contentType);
    List<Content> listByAgeRating(AgeRating ageRating);
}

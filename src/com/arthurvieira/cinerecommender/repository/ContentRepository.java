package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.AgeRating;
import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.ContentType;
import com.arthurvieira.cinerecommender.domain.Genre;

import java.util.List;

public interface ContentRepository {
    Content save(Content content);
    void deleteById(long id);
    Content findById(long id);
    List<Content> listAll();
    List<Content> findByGenre(Genre genre);
    List<Content> findByType(ContentType contentType);
    List<Content> findByAgeRating(AgeRating ageRating);
}
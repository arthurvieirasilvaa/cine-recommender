package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.repository.ContentRepository;
import com.arthurvieira.cinerecommender.repository.RatingRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationService {
    private final ContentRepository contentRepository;
    private final RatingRepository ratingRepository;

    public RecommendationService(ContentRepository contentRepository, RatingRepository ratingRepository) {
        this.contentRepository = contentRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<Content> getTopRated(int n) {
        return contentRepository.listAll()
                .stream()
                .filter(content -> !content.getRatings().isEmpty())
                .sorted(Comparator.comparing(Content::getAverageRating).reversed())
                .limit(n)
                .toList();
    }
}
package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.Genre;
import com.arthurvieira.cinerecommender.domain.Rating;
import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.exception.UserWithNoRatings;
import com.arthurvieira.cinerecommender.repository.ContentRepository;
import com.arthurvieira.cinerecommender.repository.RatingRepository;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationService {
    private final ContentRepository contentRepository;
    private final RatingRepository ratingRepository;

    public RecommendationService(ContentRepository contentRepository, RatingRepository ratingRepository) {
        this.contentRepository = contentRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<Content> getTopRated(int n) {
        return this.contentRepository.listAll()
                .stream()
                .filter(content -> !content.getRatings().isEmpty())
                .sorted(Comparator.comparing(Content::getAverageRating).reversed())
                .limit(n)
                .toList();
    }

    private Genre getUserFavoriteGenre(List<Rating> userRatings) {
        // Collecting the number os elements of each genre:
        Map<Genre, Long> genresCollect = userRatings
                .stream()
                .collect(Collectors.groupingBy(rating -> rating.getContent().getGenre(),
                        Collectors.counting()));

        // Returning user's favorite genre:
        return genresCollect.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new UserWithNoRatings("O usuário não possui avaliações!"));
    }

    public List<Content> recommendByFavoriteGenre(User user) {
        // Gettings only the ratings made by the user:
        List<Rating> userRatings = this.ratingRepository.listAll().stream()
                .filter(rating -> rating.getUser().equals(user))
                .toList();

        // Getting the ids of the contents the user has watched:
        Set<Long> watchedContentsIds = userRatings
                .stream()
                .map(rating -> rating.getContent().getId())
                .collect(Collectors.toSet());

        Genre favoriteGenre = this.getUserFavoriteGenre(userRatings);

        // Returning 10 contents the user hasn't watched based of his favorite genre:
        return this.contentRepository.listAll()
                .stream()
                .filter(content -> !watchedContentsIds.contains(content.getId()))
                .filter(content -> content.getGenre() == favoriteGenre)
                .sorted(Comparator.comparing(Content::getAverageRating).reversed())
                .limit(10)
                .toList();
    }
}
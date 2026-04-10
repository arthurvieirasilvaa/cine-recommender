package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.*;
import com.arthurvieira.cinerecommender.exception.UserWithNoRatings;
import com.arthurvieira.cinerecommender.repository.ContentRepository;
import com.arthurvieira.cinerecommender.repository.RatingRepository;

import java.util.*;
import java.util.function.Predicate;
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

    private List<Rating> getUserRatings(User user) {
        return this.ratingRepository.listAll()
                .stream()
                .filter(rating -> rating.getUser().equals(user))
                .toList();
    }

    private Set<Long> getUserWatchedContentsIds(List<Rating> userRatings) {
        return userRatings.stream()
                .map(rating -> rating.getContent().getId())
                .collect(Collectors.toSet());
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

    private List<Content> filterAndSortContents(Set<Long> watchedContentsIds, Predicate<Content> filter) {
        return this.contentRepository.listAll()
                .stream()
                .filter(content -> !watchedContentsIds.contains(content.getId()))
                .filter(filter)
                .sorted(Comparator.comparing(Content::getAverageRating).reversed())
                .limit(10)
                .toList();
    }

    public List<Content> recommendByFavoriteGenre(User user) {
        List<Rating> userRatings = this.getUserRatings(user);

        if (userRatings.isEmpty()) {
            throw new UserWithNoRatings("O usuário não possui avaliações!");
        }

        // Getting the ids of the contents the user has watched:
        Set<Long> watchedContentsIds = this.getUserWatchedContentsIds(userRatings);

        Genre favoriteGenre = this.getUserFavoriteGenre(userRatings);

        // Returning 10 contents the user hasn't watched based of his favorite genre:
        return this.filterAndSortContents(watchedContentsIds, content -> content.getGenre() == favoriteGenre);
    }

    public List<Content> recommendByHistory(User user) {
        List<Rating> userRatings = getUserRatings(user);

        // Getting the ratings made by the user with average >= 4.0:
        List<Rating> topRatings = userRatings
                .stream()
                .filter(rating -> rating.getContent().getAverageRating() >= 4.0)
                .toList();

        // Creating a set with content's genres the user has watched:
        Set<Genre> genres = topRatings
                .stream()
                .map(rating -> rating.getContent().getGenre())
                .collect(Collectors.toSet());

        // Creating a set with content's age ratings the user has watched:
        Set<AgeRating> ageRatings = topRatings
                .stream()
                .map(rating -> rating.getContent().getAgeRating())
                .collect(Collectors.toSet());

        // Getting the ids of the contents the user has watched:
        Set<Long> watchedContentsIds = this.getUserWatchedContentsIds(userRatings);

        // Returning 10 contents the user hasn't watched based on genres and age ratings
        // of the contents the user has watched:
        return this.filterAndSortContents(watchedContentsIds,
                content -> genres.contains(content.getGenre())
                        && ageRatings.contains(content.getAgeRating()));
    }
}
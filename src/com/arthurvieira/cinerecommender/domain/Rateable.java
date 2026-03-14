package com.arthurvieira.cinerecommender.domain;

import java.util.List;

public interface Rateable {
    void addRating(Rating rating);
    List<Rating> getRatings();
}
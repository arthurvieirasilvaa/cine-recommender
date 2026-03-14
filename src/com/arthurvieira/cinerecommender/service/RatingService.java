package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.Rating;
import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.repository.RatingRepository;

import java.util.List;

public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating createOrUpdateRating(User user, Content content, int stars) {
        // Filters if the rating was already created:
        List<Rating> ratingFiltered = this.ratingRepository.filter(rating -> rating.getUser().equals(user) && rating.getContent().equals(content));

        Rating rating;

        if(ratingFiltered.isEmpty()) {
            rating = new Rating(0, user, content, stars); // the rating did not exist
            user.addRating(rating); // the new rating is added to the user's ratings list
        }

        else {
            rating = ratingFiltered.get(0); // obtains the existing rating
            rating.updateStars(stars);
        }

        return this.ratingRepository.save(rating);
    }
}
package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.Rateable;
import com.arthurvieira.cinerecommender.domain.Rating;
import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.repository.RatingRepository;
import com.arthurvieira.cinerecommender.util.ValidationUtils;

import java.util.List;
import java.util.function.Function;

public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public <T extends Rateable> void syncRatings(List<T> allObjects, Function<Rating, T> function) {
        List<Rating> allRatings = this.ratingRepository.listAll();

        for(Rating rating : allRatings) {
            T object = function.apply(rating);
            for(T t : allObjects) {
                if(t.equals(object)) {
                    t.addRating(rating);
                    break;
                }
            }
        }
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

    public Rating deleteRating(long id) {
        ValidationUtils.validateId(id);
        return this.ratingRepository.deleteById(id);
    }

    public List<Rating> getUserRatingHistory(User user) {
        return user.getRatings();
    }
}

package com.arthurvieira.cinerecommender.domain;

import java.util.Objects;

public class Rating {
    private static final int MIN_STARS = 0;
    private static final int MAX_STARS = 5;

    private final long userId;
    private final long contentId;
    private int stars;

    public Rating(long userId, long contentId, int stars) {
        validadeStars(stars);
        this.userId = userId;
        this.contentId = contentId;
        this.stars = stars;
    }

    private void validadeStars(int stars) {
        if(stars < MIN_STARS || stars > MAX_STARS) {
            throw new IllegalArgumentException("The stars must betwenn "+MIN_STARS+" and "+MAX_STARS);
        }
    }

    public void updateStars(int stars) {
        validadeStars(stars);
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "userId=" + userId +
                ", contentId=" + contentId +
                ", stars=" + stars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return userId == rating.userId && contentId == rating.contentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contentId);
    }

    public long getUserId() {
        return userId;
    }

    public long getContentId() {
        return contentId;
    }

    public int getStars() {
        return stars;
    }
}

package com.arthurvieira.cinerecommender.domain;

import java.util.Objects;

public class Rating {
    private final long userId;
    private final long contentId;
    private int score;

    public Rating(long userId, long contentId, int score) {
        this.userId = userId;
        this.contentId = contentId;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "userId=" + userId +
                ", contentId=" + contentId +
                ", score=" + score +
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
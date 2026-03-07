package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.util.ValidationUtils;

import java.util.Objects;

public class Rating {
    private final User user;
    private final Content content;
    private int stars;

    public Rating(User user, Content content, int stars) {
        ValidationUtils.validadeStars(stars);
        this.user = Objects.requireNonNull(user, "O usuário não pode ser nulo");
        this.content = Objects.requireNonNull(content, "O conteúdo não pode ser nulo!");
        this.stars = stars;
    }

    public void updateStars(int stars) {
        ValidationUtils.validadeStars(stars);
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "userId=" + user.getId() +
                ", contentId=" + content.getId() +
                ", stars=" + stars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(user, rating.user) && Objects.equals(content, rating.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, content);
    }

    public User getUser() {
        return user;
    }

    public Content getContent() {
        return content;
    }

    public int getStars() {
        return stars;
    }
}
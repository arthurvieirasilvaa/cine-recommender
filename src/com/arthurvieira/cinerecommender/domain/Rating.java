package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.util.ValidationUtils;

import java.util.Objects;

public class Rating {
    private long id;
    private final User user;
    private final Content content;
    private int stars;

    public Rating(long id, User user, Content content, int stars) {
        ValidationUtils.validateId(id);
        this.id = id;

        this.user = Objects.requireNonNull(user, "O usuário não pode ser nulo");
        this.content = Objects.requireNonNull(content, "O conteúdo não pode ser nulo!");

        ValidationUtils.validadeStars(stars);
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

    public void printRating() {
        System.out.println("\n\t-> Avaliação com ID "+getId()+":");
        this.getContent().printSummary();
        System.out.println("\t\t - Nota: "+getStars()+"/5\n");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
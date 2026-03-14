package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.util.ValidationUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Rateable {
    private long id;
    private String name;
    private final String email;
    private final LocalDate registrationDate;
    private final List<Rating> ratings;

    public User(long id, String name, String email, LocalDate registrationDate) {
        ValidationUtils.validateId(id);
        this.id = id;
        this.name = name;

        ValidationUtils.validateEmail(email);
        this.email = email;

        this.registrationDate = registrationDate;
        this.ratings = new ArrayList<>();
    }

    public User(long id, String name, String email) {
        this(id, name, email, LocalDate.now());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", ratings=" + ratings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    public String formatDate() {
        String DATE_PATTERN = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return this.registrationDate.format(formatter);
    }

    @Override
    public void addRating(Rating rating) {
        if(!this.ratings.contains(rating)) {
            this.ratings.add(rating);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public List<Rating> getRatings() {
        return List.copyOf(ratings);
    }
}
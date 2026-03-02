package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.exception.InvalidEmailException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String name;
    private final String email;
    LocalDate registrationDate;
    private final List<Rating> ratings;

    private final String EMAIL_REGEX = "^([a-zA-Z0-9._-])+@([a-zA-Z])+(\\.([a-zA-Z])+)+$";
    private final String DATE_PATTERN = "dd-MMMM-yyyy";

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;

        validateEmail(email);
        this.email = email;

        this.registrationDate = LocalDate.now();
        this.ratings = new ArrayList<>();
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

    public void validateEmail(String email) {
        if(email == null || !email.matches(EMAIL_REGEX)) {
            throw new InvalidEmailException("O email informado está inválido!");
        }
    }

    public String formatDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return this.registrationDate.format(formatter);
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

    public List<Rating> getRatings() {
        return List.copyOf(ratings);
    }
}

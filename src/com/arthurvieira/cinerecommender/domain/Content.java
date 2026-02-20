package com.arthurvieira.cinerecommender.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public abstract class Content {
    protected long id;
    protected String title;
    protected LocalDate releaseYear;
    protected Genre genre;
    protected AgeRating ageRating;
    protected ContentType contentType;
    protected List<Rating> ratings;

    public Content(long id, String title, LocalDate releaseYear, ContentType contentType) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.contentType = contentType;
    }

    public Content(long id, String title, LocalDate releaseYear, Genre genre, ContentType contentType) {
        this(id, title, releaseYear, contentType);
        this.contentType = contentType;
    }

    public Content(long id, String title, LocalDate releaseYear, ContentType contentType, Genre genre, AgeRating ageRating) {
        this(id, title, releaseYear, genre, contentType);
        this.ageRating = ageRating;
    }

    public Content(long id, String title, LocalDate releaseYear, Genre genre, AgeRating ageRating, ContentType contentType, List<Rating> ratings) {
        this(id, title, releaseYear, contentType, genre, ageRating);
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return id == content.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
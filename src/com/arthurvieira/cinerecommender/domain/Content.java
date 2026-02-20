package com.arthurvieira.cinerecommender.domain;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Content {
    protected long id;
    protected String title;
    protected Year releaseYear;
    protected Genre genre;
    protected AgeRating ageRating;
    protected ContentType contentType;
    protected List<Rating> ratings;

    public Content(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, ContentType contentType) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank!");
        }

        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.ageRating = ageRating;
        this.contentType = contentType;
        this.ratings = new ArrayList<>();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
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
        return List.copyOf(ratings);
    }
}
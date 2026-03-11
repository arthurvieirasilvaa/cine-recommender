package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.util.ValidationUtils;

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

    protected Content(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, ContentType contentType) {
        ValidationUtils.validateId(id);
        this.id = id;
        this.title = title;

        ValidationUtils.validateReleaseYear(releaseYear);
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

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public List<Rating> getRatings() {
        return List.copyOf(ratings);
    }
}
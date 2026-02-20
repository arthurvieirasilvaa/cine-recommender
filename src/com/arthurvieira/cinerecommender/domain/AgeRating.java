package com.arthurvieira.cinerecommender.domain;

public enum AgeRating {
    G("General Audiences (G)"),
    PG("Parental Guidance Suggested (PG)"),
    PG_13("Parents Strongly Cautioned (PG-13)"),
    R("Restricted (R)"),
    NC_17("No One 17 and Under Admitted (NC-17)");

    private final String description;

    AgeRating(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

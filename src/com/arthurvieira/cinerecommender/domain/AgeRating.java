package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.exception.InvalidAgeRatingException;

public enum AgeRating {
    G("General Audiences (G)", 0),
    PG("Parental Guidance Suggested (PG)", 7),
    PG_13("Parents Strongly Cautioned (PG-13)", 13),
    R("Restricted (R)", 17),
    NC_17("No One 17 and Under Admitted (NC-17)", 18);

    private final String description;
    private final int minimumAge;

    AgeRating(String description, int minimumAge) {
        this.description = description;
        this.minimumAge = minimumAge;
    }

    public boolean isAllowedFor(int age) {
        return age >= this.minimumAge;
    }

    public static AgeRating fromOption(int option) {
        AgeRating[] ageRatingsValues = AgeRating.values();

        if(option < 1 || option > ageRatingsValues.length) {
            throw new InvalidAgeRatingException("Opção inválida para a classificação indicativa!");
        }

        return ageRatingsValues[option-1];
    }

    public String getDescription() {
        return description;
    }

    public int getMinimumAge() {
        return minimumAge;
    }
}

package com.arthurvieira.cinerecommender.domain;

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

    public static AgeRating ageRatingFromDescription(String description) {
        for(AgeRating ageRating : AgeRating.values()) {
            if(ageRating.getDescription().equals(description)) {
                return ageRating;
            }
        }

        throw new IllegalArgumentException("Invalid age rating: "+description);
    }

    public String getDescription() {
        return description;
    }

    public int getMinimumAge() {
        return minimumAge;
    }
}

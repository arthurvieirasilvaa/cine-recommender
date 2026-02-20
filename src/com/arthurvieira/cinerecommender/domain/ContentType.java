package com.arthurvieira.cinerecommender.domain;

public enum ContentType {
    MOVIE("Movie"),
    SERIES("Series");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public static ContentType contentTypeFromType(String type) {
        for(ContentType contentType : ContentType.values()) {
            if(contentType.getType().equals(type)) {
                return contentType;
            }
        }

        throw new IllegalArgumentException("Invalid content type: "+type);
    }

    public String getType() {
        return type;
    }
}
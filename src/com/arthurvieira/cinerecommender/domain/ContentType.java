package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.exception.InvalidContentTypeException;

public enum ContentType {
    MOVIE("Movie"),
    SERIES("Series");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public static ContentType fromType(String type) {
        for(ContentType contentType : ContentType.values()) {
            if(contentType.getType().equals(type)) {
                return contentType;
            }
        }

        throw new IllegalArgumentException("Invalid content type: "+type);
    }

    public static ContentType fromOption(int option) {
        ContentType[] contentTypes = ContentType.values();

        if(option < 1 || option > contentTypes.length) {
            throw new InvalidContentTypeException("Opção inválida para o tipo!");
        }

        return contentTypes[option-1];
    }

    public String getType() {
        return type;
    }
}
package com.arthurvieira.cinerecommender.util;

import com.arthurvieira.cinerecommender.exception.*;

import java.time.Duration;
import java.time.Year;

public class ValidationUtils {
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9._-])+@([a-zA-Z])+(\\.([a-zA-Z])+)+$";
    private static final int MIN_STARS = 0;
    private static final int MAX_STARS = 5;

    private ValidationUtils() {
    }

    public static void validateId(long id) {
        if(id <= 0) {
            throw new InvalidIdException("O ID deve ser positivo!");
        }
    }

    public static void validateReleaseYear(Year releaseYear) {
        if(releaseYear.isAfter(Year.now())) {
            throw new InvalidReleaseYearException("O ano de lançamento informado está inválido!");
        }
    }

    public static void validateMovieDuration(Duration duration) {
        if(duration.isNegative() || duration.isZero()) {
            throw new InvalidDurationException("A duração do filme deve ser positiva!");
        }
    }

    public static void validateSeriesNumberOfSeasons(int numberOfSeasons) {
        if(numberOfSeasons <= 0) {
            throw new InvalidNumberOfSeasonsException("O número de temporadas deve ser positivo!");
        }
    }

    public static void validateSeriesTotalEpisodes(int totalEpisodes) {
        if(totalEpisodes <= 0) {
            throw new InvalidTotalEpisodesException("O total de episódios deve ser positivo!");
        }
    }

    public static void validateEmail(String email) {
        if(email == null || !email.matches(EMAIL_REGEX)) {
            throw new InvalidEmailException("O email informado está inválido!");
        }
    }

    public static void validadeStars(int stars) {
        if(stars < MIN_STARS || stars > MAX_STARS) {
            throw new IllegalArgumentException("The stars must betwenn "+MIN_STARS+" and "+MAX_STARS);
        }
    }
}

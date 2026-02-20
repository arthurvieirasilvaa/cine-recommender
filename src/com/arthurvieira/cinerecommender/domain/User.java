package com.arthurvieira.cinerecommender.domain;

import java.time.LocalDate;
import java.util.List;

public class User {
    private long id;
    private String name;
    private String email;
    LocalDate registrationDate;
    private List<Rating> ratings;
}
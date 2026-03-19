package com.arthurvieira.cinerecommender.service;

import com.arthurvieira.cinerecommender.repository.ContentRepository;
import com.arthurvieira.cinerecommender.repository.UserRepository;

public class RecommendationService {
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public RecommendationService(ContentRepository contentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }
}
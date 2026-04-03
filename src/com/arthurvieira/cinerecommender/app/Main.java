package com.arthurvieira.cinerecommender.app;

import com.arthurvieira.cinerecommender.controller.*;
import com.arthurvieira.cinerecommender.domain.Rating;
import com.arthurvieira.cinerecommender.repository.*;
import com.arthurvieira.cinerecommender.service.ContentService;
import com.arthurvieira.cinerecommender.service.RatingService;
import com.arthurvieira.cinerecommender.service.RecommendationService;
import com.arthurvieira.cinerecommender.service.UserService;
import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Paths:
        Path contentPath = Paths.get("data/contents.txt");
        Path userPath = Paths.get("data/users.txt");
        Path ratingPath = Paths.get("data/ratings.txt");

        // UI:
        ConsoleMenu consoleMenu = new ConsoleMenu();
        InputHandler inputHandler = new InputHandler();

        // Repository:
        ContentRepository contentRepository = new FileContentRepository(contentPath);
        UserRepository userRepository = new FileUserRepository(userPath);
        RatingRepository ratingRepository = new FileRatingRepository(ratingPath, contentRepository, userRepository);

        // Service:
        ContentService contentService = new ContentService(contentRepository);
        UserService userService = new UserService(userRepository);
        RatingService ratingService = new RatingService(ratingRepository);
        RecommendationService recommendationService = new RecommendationService(contentRepository, ratingRepository);

        // Data synchronization:
        ratingService.syncRatings(contentService.listAll(), Rating::getContent);
        ratingService.syncRatings(userService.listUsers(), Rating::getUser);

        // Controller:
        RatingController ratingController = new RatingController(inputHandler, ratingService    , contentService, userService);
        ContentController contentController = new ContentController(consoleMenu, inputHandler, contentService, ratingController);
        UserController userController = new UserController(consoleMenu, inputHandler, userService, ratingController);
        RecommendationController recommendationController = new RecommendationController(consoleMenu, inputHandler, recommendationService, userService);
        ConsoleController consoleController = new ConsoleController(consoleMenu, inputHandler, contentController, userController, recommendationController);
        consoleController.start();

        inputHandler.closeScanner();
    }
}
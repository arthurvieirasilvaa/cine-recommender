package com.arthurvieira.cinerecommender.app;

import com.arthurvieira.cinerecommender.controller.ConsoleController;
import com.arthurvieira.cinerecommender.controller.ContentController;
import com.arthurvieira.cinerecommender.controller.UserController;
import com.arthurvieira.cinerecommender.repository.FileContentRepository;
import com.arthurvieira.cinerecommender.repository.FileUserRepository;
import com.arthurvieira.cinerecommender.service.ContentService;
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

        // UI:
        ConsoleMenu consoleMenu = new ConsoleMenu();
        InputHandler inputHandler = new InputHandler();

        // Repository:
        ContentRepository contentRepository = new FileContentRepository(contentPath);
        UserRepository userRepository = new FileUserRepository(userPath);

        // Service:
        ContentService contentService = new ContentService(contentRepository);
        UserService userService = new UserService(userRepository);

        // Controller:
        ContentController contentController = new ContentController(consoleMenu, inputHandler, contentService);
        UserController userController = new UserController(consoleMenu, inputHandler, userService);
        ConsoleController consoleController = new ConsoleController(consoleMenu, inputHandler, contentController, userController);
        consoleController.start();

        inputHandler.closeScanner();
    }
}
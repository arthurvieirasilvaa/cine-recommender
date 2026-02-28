package com.arthurvieira.cinerecommender.app;

import com.arthurvieira.cinerecommender.controller.ConsoleController;
import com.arthurvieira.cinerecommender.controller.ContentController;
import com.arthurvieira.cinerecommender.repository.ContentRepository;
import com.arthurvieira.cinerecommender.repository.FileContentRepository;
import com.arthurvieira.cinerecommender.service.ContentService;
import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path contentPath = Paths.get("data/contents.txt");

        // UI:
        ConsoleMenu consoleMenu = new ConsoleMenu();
        InputHandler inputHandler = new InputHandler();

        // Repository:
        ContentRepository contentRepository = new FileContentRepository(contentPath);

        // Service:
        ContentService contentService = new ContentService(contentRepository);

        // Controller:
        ContentController contentController = new ContentController(new ConsoleMenu(), new InputHandler(), contentService);
        ConsoleController consoleController = new ConsoleController(new ConsoleMenu(), new InputHandler(), contentController);
        consoleController.start();
    }
}

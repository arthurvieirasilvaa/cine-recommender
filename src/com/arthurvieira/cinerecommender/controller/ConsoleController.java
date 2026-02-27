package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

public class ConsoleController {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;
    private final ContentController contentController;

    public ConsoleController(ConsoleMenu consoleMenu,
                             InputHandler inputHandler,
                             ContentController contentController) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
        this.contentController = contentController;
    }

    public void start() {
        int option;

        do {
            this.consoleMenu.showMainMenu();
            option = this.inputHandler.readInt();

            switch (option) {
                case 1:
                    this.contentController.start();
                    break;
                case 2:
                    this.consoleMenu.showRecommendationsMenu();
                    break;
                case 3:
                    // start UserController
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != MenuOptions.EXIT);
    }
}
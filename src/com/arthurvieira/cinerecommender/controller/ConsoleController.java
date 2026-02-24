package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;

public class ConsoleController {
    private static final short EXIT_OPTION = 0;
    private static final short BACK_OPTION = -1;

    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;

    public ConsoleController() {
        this.consoleMenu = new ConsoleMenu();
        this.inputHandler = new InputHandler();
    }

    public void start() {
        int option;

        do {
            this.consoleMenu.showMainMenu();
            option = this.inputHandler.readOption();

            switch (option) {
                case 1:
                    // start ContentController
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
        } while (option != EXIT_OPTION);
    }
}

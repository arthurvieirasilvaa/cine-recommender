package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

public class ConsoleController {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;
    private final ContentController contentController;
    private final UserController userController;

    public ConsoleController(ConsoleMenu consoleMenu,
                             InputHandler inputHandler,
                             ContentController contentController,
                             UserController userController) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
        this.contentController = contentController;
        this.userController = userController;
    }

    public void start() {
        boolean running = true;
        int option;

        while (running){
            this.consoleMenu.showMainMenu();
            option = this.inputHandler.readInt("Digite uma opção: ");

            switch (option) {
                case 1:
                    this.contentController.start();
                    break;
                case 2:
                    this.consoleMenu.showRecommendationsMenu();
                    break;
                case 3:
                    this.userController.start();
                    break;
                case MenuOptions.EXIT:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}

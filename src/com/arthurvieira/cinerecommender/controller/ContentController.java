package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

public class ContentController {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;

    public ContentController(ConsoleMenu consoleMenu, InputHandler inputHandler) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
    }

    public void start() {
        int option;

        do {
            this.consoleMenu.showContentMenu();
            option = this.inputHandler.readOption();

            // switch
        } while (option != MenuOptions.EXIT);
    }
}
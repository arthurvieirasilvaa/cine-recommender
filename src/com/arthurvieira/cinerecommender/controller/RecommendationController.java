package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.service.RecommendationService;
import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

import java.util.List;

public class RecommendationController implements Controller {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;
    private final RecommendationService recommendationService;

    public RecommendationController(ConsoleMenu consoleMenu, InputHandler inputHandler, RecommendationService recommendationService) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
        this.recommendationService = recommendationService;
    }

    public void start() {
        boolean running = true;
        int option;

        while (running) {
            this.consoleMenu.showRecommendationsMenu();
            option = this.inputHandler.readInt("Opção: ");

            switch (option) {
                case 1:
                    this.showTopRated();
                    break;
                case MenuOptions.BACK:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void printTopRatedContent(Content content) {
        content.printSummary();
        System.out.printf("\t\t - Média: %.1f/5%n%n", content.getAverageRating());
    }

    private void showTopRated() {
        List<Content> top10Rated = this.recommendationService.getTopRated(10);

        displayResults(top10Rated, "Ainda não há conteúdos avaliados!", this::printTopRatedContent);
    }
}

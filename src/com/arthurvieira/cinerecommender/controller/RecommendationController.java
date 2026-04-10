package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.exception.InvalidIdException;
import com.arthurvieira.cinerecommender.exception.ObjectNotExistException;
import com.arthurvieira.cinerecommender.exception.UserWithNoRatings;
import com.arthurvieira.cinerecommender.service.RecommendationService;
import com.arthurvieira.cinerecommender.service.UserService;
import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

import java.util.List;

public class RecommendationController implements Controller {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;
    private final RecommendationService recommendationService;
    private final UserService userService;

    public RecommendationController(ConsoleMenu consoleMenu,
                                    InputHandler inputHandler,
                                    RecommendationService recommendationService,
                                    UserService userService) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
        this.recommendationService = recommendationService;
        this.userService = userService;
    }

    public void start() {
        boolean running = true;
        int option;

        while (running) {
            this.consoleMenu.showRecommendationsMenu();
            option = this.inputHandler.readInt("Opção: ");

            switch (option) {
                case 1:
                    this.showTopRatedContents();
                    break;
                case 2:
                    this.showContentsByUserFavoriteGenre();
                    break;
                case 3:
                    this.showContentsByUserHistory();
                    break;
                case 4:
                    this.showNewReleasesContents();
                    break;
                case MenuOptions.BACK:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void printRecommendedContents(Content content) {
        content.printSummary();
        System.out.printf("\t\t - Média: %.1f/5%n", content.getAverageRating());
        System.out.println("\t\t - Ano de lançamento: "+content.getReleaseYear());
        System.out.println("\t\t - Gênero: "+content.getGenre().getName());
        System.out.println("\t\t - Classificação Indicativa: "+content.getAgeRating().getDescription()+"\n");
    }

    private void showTopRatedContents() {
        List<Content> top10Rated = this.recommendationService.getTopRated(10);

        displayResults(top10Rated, "Ainda não há conteúdos avaliados!", this::printRecommendedContents);
    }

    private void showContentsByUserFavoriteGenre() {
        int id = this.inputHandler.readPositiveInt("ID do usuário: ");
        try {
            User user = this.userService.filterUserById(id);

            List<Content> contentsWithUserFavoriteGenre = this.recommendationService.recommendByFavoriteGenre(user);

            displayResults(contentsWithUserFavoriteGenre, "Não foi possível recomendar nenhum conteúdo!", this::printRecommendedContents);
        } catch (InvalidIdException e) {
            System.out.println("O ID informado está inválido!");
        } catch (ObjectNotExistException e) {
            System.out.println("O usuário com ID "+id+" não existe!");
        } catch (UserWithNoRatings e) {
            System.out.println("O usuário não possui avaliações!");
        }
    }

    private void showContentsByUserHistory() {
        int id = this.inputHandler.readPositiveInt("ID do usuário: ");
        try {
            User user = this.userService.filterUserById(id);

            List<Content> contentsByUserHistory = this.recommendationService.recommendByHistory(user);

            displayResults(contentsByUserHistory, "Não foi possível recomendar nenhum conteúdo!", this::printRecommendedContents);
        } catch (InvalidIdException e) {
            System.out.println("O ID informado está inválido!");
        } catch (ObjectNotExistException e) {
            System.out.println("O usuário com ID " + id + " não existe!");
        } catch (UserWithNoRatings e) {
            System.out.println("O usuário não possui avaliações!");
        }
    }

    private void showNewReleasesContents() {
        List<Content> newReleases = this.recommendationService.recommendNewReleases();

        displayResults(newReleases,"Não foi possível encontrar os lançamentos e novidades!", this::printRecommendedContents);
    }
}
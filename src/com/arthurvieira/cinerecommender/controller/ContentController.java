package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.AgeRating;
import com.arthurvieira.cinerecommender.domain.Genre;
import com.arthurvieira.cinerecommender.domain.Movie;
import com.arthurvieira.cinerecommender.exception.InvalidDurationException;
import com.arthurvieira.cinerecommender.exception.InvalidGenreException;
import com.arthurvieira.cinerecommender.service.ContentService;
import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

import java.time.Duration;
import java.time.Year;

public class ContentController {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;
    private final ContentService contentService;

    public ContentController(ConsoleMenu consoleMenu, InputHandler inputHandler, ContentService contentService) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
        this.contentService = contentService;
    }

    public void start() {
        int option;

        do {
            this.consoleMenu.showContentMenu();
            option = this.inputHandler.readOption();

            switch (option) {
                case 1:
                    this.registerMovie();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != MenuOptions.EXIT);
    }

    private void registerMovie() {
        try {
            System.out.println("Título: ");
            String title = this.inputHandler.readText();

            System.out.println("Ano de lançamento: ");
            Year releaseYear = this.inputHandler.readYear();

            this.consoleMenu.showGenreOptions();
            int option = this.inputHandler.readOption();
            Genre genre = Genre.fromOption(option);

            this.consoleMenu.showAgeRatingOptions();
            option = this.inputHandler.readOption();
            AgeRating ageRating = AgeRating.fromOption(option);

            System.out.println("Duração (em minutos): ");
            Duration duration = this.inputHandler.readDuration();

            Movie movie = this.contentService.createMovie(title, releaseYear, genre, ageRating, duration);
            System.out.println("O filme "+title+" foi criado com sucesso!");
            System.out.println("O ID gerado foi: "+movie.getId());
        } catch (InvalidGenreException e) {
            System.out.println("O Gênero do filme está inválido!");
        } catch (InvalidDurationException e) {
            System.out.println("A duração do filme está inválida!");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao cadastrar o filme!");
        }
    }
}
package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.AgeRating;
import com.arthurvieira.cinerecommender.domain.Genre;
import com.arthurvieira.cinerecommender.domain.Movie;
import com.arthurvieira.cinerecommender.domain.Series;
import com.arthurvieira.cinerecommender.exception.*;
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
        boolean running = true;
        int option;

        while (running){
            this.consoleMenu.showContentMenu();
            option = this.inputHandler.readInt("Opção: ");

            switch (option) {
                case 1:
                    this.registerMovie();
                    break;
                case 2:
                    this.registerSeries();
                    break;
                case MenuOptions.BACK:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void registerMovie() {
        String title = this.inputHandler.readText("Título: ");
        Year releaseYear = this.inputHandler.readYear("Ano de lançamento: ");

        this.consoleMenu.showGenreOptions();
        Genre genre = this.inputHandler.readGenre("Opção do Gênero: ");

        this.consoleMenu.showAgeRatingOptions();
        AgeRating ageRating = this.inputHandler.readAgeRating("Opção da Classificação Indicativa: ");

        Duration duration = this.inputHandler.readDuration("Duração (em minutos): ");

        Movie movie = this.contentService.createMovie(title, releaseYear, genre, ageRating, duration);

        System.out.println("O filme "+title+" foi criado com sucesso!");
        System.out.println("O ID gerado foi: "+movie.getId());
    }

    private void registerSeries() {
        String title = this.inputHandler.readText("Título: ");
        Year releaseYear = this.inputHandler.readYear("Ano de lançamento: ");

        this.consoleMenu.showGenreOptions();
        Genre genre = this.inputHandler.readGenre("Opção do Gênero: ");

        this.consoleMenu.showAgeRatingOptions();
        AgeRating ageRating = this.inputHandler.readAgeRating("Opção da Classificação Indicativa: ");

        int numberOfSeasons = this.inputHandler.readPositiveInt("Número de temporadas: ");
        int totalEpisodes = this.inputHandler.readPositiveInt("Total de episódios: ");

        Series series = this.contentService.createSeries(title, releaseYear, genre, ageRating, numberOfSeasons, totalEpisodes);

        System.out.println("A série "+title+" foi criada com sucesso!");
        System.out.println("O ID gerado foi: "+series.getId());
    }
}

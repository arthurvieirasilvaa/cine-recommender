package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.*;
import com.arthurvieira.cinerecommender.exception.*;
import com.arthurvieira.cinerecommender.service.ContentService;
import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

import java.time.Duration;
import java.time.Year;
import java.util.List;

public class ContentController implements Controller {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;
    private final ContentService contentService;
    private final RatingController ratingController;

    public ContentController(ConsoleMenu consoleMenu,
                             InputHandler inputHandler,
                             ContentService contentService,
                             RatingController ratingController) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
        this.contentService = contentService;
        this.ratingController = ratingController;
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
                case 3:
                    this.ratingController.registerRating();
                    break;
                case 4:
                    this.listAllContents();
                    break;
                case 5:
                    this.searchById();
                    break;
                case 6:
                    this.delete();
                    break;
                case 7:
                    this.searchByGenre();
                    break;
                case 8:
                    this.searchByContentType();
                    break;
                case 9:
                    this.searchByAgeRating();
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
        Genre genre = this.inputHandler.readEnum("Opção do Gênero: ", Genre.values());

        this.consoleMenu.showAgeRatingOptions();
        AgeRating ageRating = this.inputHandler.readEnum("Opção da Classificação Indicativa: ", AgeRating.values());

        Duration duration = this.inputHandler.readDuration("Duração (em minutos): ");

        Movie movie = this.contentService.createMovie(title, releaseYear, genre, ageRating, duration);

        System.out.println("O filme "+title+" foi criado com sucesso!");
        System.out.println("O ID gerado foi: "+movie.getId());
    }

    private void registerSeries() {
        String title = this.inputHandler.readText("Título: ");
        Year releaseYear = this.inputHandler.readYear("Ano de lançamento: ");

        this.consoleMenu.showGenreOptions();
        Genre genre = this.inputHandler.readEnum("Opção do Gênero: ", Genre.values());

        this.consoleMenu.showAgeRatingOptions();
        AgeRating ageRating = this.inputHandler.readEnum("Opção da Classificação Indicativa: ", AgeRating.values());

        int numberOfSeasons = this.inputHandler.readPositiveInt("Número de temporadas: ");
        int totalEpisodes = this.inputHandler.readPositiveInt("Total de episódios: ");

        Series series = this.contentService.createSeries(title, releaseYear, genre, ageRating, numberOfSeasons, totalEpisodes);

        System.out.println("A série "+title+" foi criada com sucesso!");
        System.out.println("O ID gerado foi: "+series.getId());
    }

    private void listAllContents() {
        List<Content> contents = this.contentService.listAll();

        displayResults(contents, "Ainda não há conteúdo cadastrado!", Content::printContent);
    }

    private void searchById() {
        int id = this.inputHandler.readPositiveInt("ID: ");
        try {
            Content content = this.contentService.filterContentById(id);
            content.printContent();
        } catch (InvalidIdException e) {
            System.out.println("O ID informado está inválido!");
        } catch (ObjectNotExistException e) {
            System.out.println("O conteúdo com ID "+id+" não existe!");
        }
    }

    private void delete() {
        int id = this.inputHandler.readPositiveInt("ID: ");
        try {
            Content content = this.contentService.deleteContent(id);
            System.out.println("O conteúdo "+content.getTitle()+" com ID "+content.getId()+" foi removido com sucesso!");
        } catch (InvalidIdException e) {
            System.out.println("O ID informado está inválido!");
        } catch (ObjectNotExistException e) {
            System.out.println("O conteúdo com ID "+id+" não existe!");
        }
    }

    private void searchByGenre() {
        this.consoleMenu.showGenreOptions();
        Genre genre = this.inputHandler.readEnum("Gênero: ", Genre.values());

        List<Content> contents = this.contentService.filterContentsByGenre(genre);
        displayResults(contents, "Ainda não há conteúdo de "+genre.getName()+" cadastrado!", Content::printContent);
    }

    private void searchByContentType() {
        this.consoleMenu.showContentTypeOptions();
        ContentType contentType = this.inputHandler.readEnum("Tipo: ", ContentType.values());

        List<Content> contents = this.contentService.filterContentsByContentType(contentType);

        displayResults(contents, "Ainda não há conteúdo do tipo "+contentType.getType()+" cadastrado!", Content::printContent);
    }

    private void searchByAgeRating() {
        this.consoleMenu.showAgeRatingOptions();
        AgeRating ageRating = this.inputHandler.readEnum("Classificação Indicativa: ", AgeRating.values());

        List<Content> contents = this.contentService.filterContentsByAgeRating(ageRating);

        displayResults(contents, "Ainda não há conteúdo da classificação indicativa "+ageRating.getDescription()+" cadastrado!", Content::printContent);
    }
}

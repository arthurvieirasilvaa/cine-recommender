package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.exception.InvalidIdException;
import com.arthurvieira.cinerecommender.exception.ObjectNotExistException;
import com.arthurvieira.cinerecommender.service.UserService;
import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

import java.util.List;

public class UserController implements Controller {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;
    private final UserService userService;
    private final RatingController ratingController;

    public UserController(ConsoleMenu consoleMenu,
                          InputHandler inputHandler,
                          UserService userService,
                          RatingController ratingController) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
        this.userService = userService;
        this.ratingController = ratingController;
    }

    public void start() {
        boolean running = true;
        int option;

        while (running) {
            this.consoleMenu.showUserMenu();
            option = this.inputHandler.readInt("Opção: ");

            switch (option) {
                case 1:
                    this.registerUser();
                    break;
                case 2:
                    this.listUsers();
                    break;
                case 3:
                    this.searchById();
                    break;
                case 4:
                    this.delete();
                    break;
                case 5:
                    this.ratingController.showUserRatingHistory();
                    break;
                case 6:
                    this.ratingController.delete();
                    break;
                case MenuOptions.BACK:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void registerUser() {
        String name = this.inputHandler.readText("Nome: ");
        String email = this.inputHandler.readEmail("Email: ");
        User user = this.userService.createUser(name, email);
        System.out.println("O usuário "+name+" com email "+email+" foi criado com sucesso!");
        System.out.println("O ID gerado foi: "+user.getId());
    }

    private void listUsers() {
        List<User> users = this.userService.listUsers();

        displayResults(users, "Ainda não há usuário cadastrado!", user -> System.out.println(user.formatUser()));
    }

    public void searchById() {
        int id = this.inputHandler.readPositiveInt("ID: ");
        try {
            User user = this.userService.filterUserById(id);
            System.out.println(user.formatUser());
        } catch (InvalidIdException e) {
            System.out.println("O ID informado está inválido!");
        } catch (ObjectNotExistException e) {
            System.out.println("O usuário com ID "+id+" não existe!");
        }
    }

    private void delete() {
        int id = this.inputHandler.readPositiveInt("ID: ");
        try {
            User user = this.userService.deleteUser(id);
            System.out.println("O usuário "+user.getName()+" com ID "+user.getId()+" foi removido com sucesso!");
        } catch (InvalidIdException e) {
            System.out.println("O ID informado está inválido!");
        } catch (ObjectNotExistException e) {
            System.out.println("O usuário com ID "+id+" não existe!");
        }
    }
}
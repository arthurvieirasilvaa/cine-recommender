package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.exception.InvalidIdException;
import com.arthurvieira.cinerecommender.exception.ObjectNotExistException;
import com.arthurvieira.cinerecommender.service.UserService;
import com.arthurvieira.cinerecommender.ui.ConsoleMenu;
import com.arthurvieira.cinerecommender.ui.InputHandler;
import com.arthurvieira.cinerecommender.ui.MenuOptions;

import java.util.List;

public class UserController {
    private final ConsoleMenu consoleMenu;
    private final InputHandler inputHandler;
    private final UserService userService;

    public UserController(ConsoleMenu consoleMenu, InputHandler inputHandler, UserService userService) {
        this.consoleMenu = consoleMenu;
        this.inputHandler = inputHandler;
        this.userService = userService;
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

    private void printUser(User user) {
        System.out.println("\tID: "+user.getId()+"\n"+
                "\tNome: "+user.getName()+"\n"+
                "\tEmail: "+user.getEmail()+"\n"+
                "\tData do cadastro: "+user.formatDate()+"\n");
    }

    private void listUsers() {
        List<User> users = this.userService.listUsers();

        if(users.isEmpty()) {
            System.out.println("Ainda não há usuário cadastrado!");
            return;
        }

        System.out.println("\n--- "+users.size()+" Resultados encontrados "+"---");
        for(User user : users) {
            printUser(user);
        }
    }

    private void searchById() {
        long id = this.inputHandler.readPositiveInt("ID: ");
        try {
            User user = this.userService.filterUserById(id);
            printUser(user);
        } catch (InvalidIdException e) {
            System.out.println("O ID informado está inválido!");
        } catch (ObjectNotExistException e) {
            System.out.println("O usuário com ID "+id+" não existe!");
        }
    }
}

package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.User;
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
                case MenuOptions.BACK:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void registerUser() {
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

    public void listUsers() {
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
}
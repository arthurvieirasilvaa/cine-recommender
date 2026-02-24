package com.arthurvieira.cinerecommender.ui;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int readOption() {
        while(true) {
            try {
                return Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
            }
        }
    }

    public String readText() {
        String text = "";
        do {
            text = this.scanner.nextLine().trim();
        } while (text.isEmpty());

        return text;
    }
}
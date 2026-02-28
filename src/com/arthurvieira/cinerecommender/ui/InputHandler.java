package com.arthurvieira.cinerecommender.ui;

import java.time.Duration;
import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int readInt() {
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

    public Year readYear() {
        while (true) {
            try {
                String yearString = this.readText();
                return Year.parse(yearString);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de ano inválido! Use (yyyy)");
            }
        }
    }

    public Duration readDuration() {
        while (true) {
            try {
                String minutesString = this.readText();
                long minutes = Long.parseLong(minutesString);

                if(minutes <= 0) {
                    System.out.println("A duração deve ser positiva!");
                    continue;
                }

                return Duration.ofMinutes(minutes);
            } catch (NumberFormatException e) {
                System.out.println("Duração inválida! Informe a duração em minutos");
            }
        }
    }
}
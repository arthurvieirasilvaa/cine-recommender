package com.arthurvieira.cinerecommender.ui;

import com.arthurvieira.cinerecommender.domain.AgeRating;
import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.ContentType;
import com.arthurvieira.cinerecommender.domain.Genre;

import java.time.Duration;
import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int readInt(String message) {
        while(true) {
            System.out.print(message);
            try {
                return Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
            }
        }
    }

    public int readPositiveInt(String message) {
        int value;
        while (true) {
            System.out.print(message);
            try {
                value = Integer.parseInt(this.scanner.nextLine());

                if(value > 0) {
                    return value;
                }

                System.out.println("O valor deve ser positivo!");
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
            }
        }
    }

    public String readText(String message) {
        String text = "";
        do {
            System.out.print(message);
            text = this.scanner.nextLine().trim();
        } while (text.isEmpty());

        return text;
    }

    public Year readYear(String message) {
        while (true) {
            try {
                String yearString = this.readText(message);
                return Year.parse(yearString);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de ano inválido! Use (yyyy)");
            }
        }
    }

    public Genre readGenre(String message) {
        int option;
        while (true) {
            option = this.readInt(message);

            if(option >= 1 && option <= Genre.values().length) {
                return Genre.fromOption(option);
            }

            System.out.println("O Gênero está inválido!");
        }
    }

    public AgeRating readAgeRating(String message) {
        int option;
        while (true) {
            option = this.readInt(message);

            if(option >= 1 && option <= AgeRating.values().length) {
                return AgeRating.values()[option-1];
            }

            System.out.println("A classificação indicativa está inválida!");
        }
    }

    public Duration readDuration(String message) {
        while (true) {
            try {
                String minutesString = this.readText(message);
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

    public ContentType readContentType(String message) {
        int option;
        while (true) {
            option = this.readInt(message);

            if(option >= 1 && option <= ContentType.values().length) {
                return ContentType.values()[option-1];
            }

            System.out.println("O tipo está inválido!");
        }
    }
}
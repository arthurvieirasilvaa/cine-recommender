package com.arthurvieira.cinerecommender.ui;

import com.arthurvieira.cinerecommender.exception.InvalidEmailException;
import com.arthurvieira.cinerecommender.util.ValidationUtils;

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

    public <T extends Enum<T>> T readEnum(String message, T[] values) {
        int option;
        while (true) {
            option = this.readInt(message);

            if(option >= 1 && option <= values.length) {
                return values[option-1];
            }

            System.out.println("Selecione uma opção válida entre 1 e "+values.length);
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

    public String readEmail(String message) {
        while (true) {
            try {
                String email = this.readText(message);
                ValidationUtils.validateEmail(email);
                return email;
            } catch (InvalidEmailException e) {
                System.out.println("O email informado está inválido!");
            }
        }
    }

    public int readStars(String message) {
        while (true) {
            try {
                int stars = this.readPositiveInt(message);
                ValidationUtils.validadeStars(stars);
                return stars;
            } catch (IllegalArgumentException e) {
                System.out.println("A nota da avaliação deve estar entre 1 e 5!");
            }
        }
    }

    public void closeScanner() {
        this.scanner.close();
    }
}
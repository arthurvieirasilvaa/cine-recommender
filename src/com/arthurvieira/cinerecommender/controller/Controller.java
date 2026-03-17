package com.arthurvieira.cinerecommender.controller;

import java.util.List;
import java.util.function.Consumer;

public interface Controller {
    default <T> void displayResults(List<T> list, String emptyMessage, Consumer<T> printer) {
        if(list.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        System.out.println("\n--- "+list.size()+" Resultados encontrados "+"---");
        for(T t : list) {
            printer.accept(t);
        }
    }
}
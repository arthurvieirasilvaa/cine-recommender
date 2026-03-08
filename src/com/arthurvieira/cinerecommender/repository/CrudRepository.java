package com.arthurvieira.cinerecommender.repository;

import java.util.List;
import java.util.function.Predicate;

public interface CrudRepository<T> {
    T save(T object);
    T deleteById(long id);
    T findById(long id);
    List<T> listAll();
    List<T> filter(Predicate<T> predicate);
}
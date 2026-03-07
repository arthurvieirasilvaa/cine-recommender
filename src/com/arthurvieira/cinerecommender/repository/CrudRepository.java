package com.arthurvieira.cinerecommender.repository;

import java.util.List;

public interface CrudRepository<T> {
    T save(T object);
    T deleteById(long id);
    T findById(long id);
    List<T> listAll();
}
package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    Meal getMealById(Long idMeal);

    List<Meal> getAll();

    void create(Meal meal);

    void update(Meal meal);

    void delete(Long idMeal);
}

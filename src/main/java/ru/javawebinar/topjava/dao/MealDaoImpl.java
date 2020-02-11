package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MealDaoImpl implements MealDao {

    private ConcurrentMap<Long, Meal> meals;
    private final AtomicLong counter = new AtomicLong();

    public MealDaoImpl() {
        meals = new ConcurrentHashMap(MealsUtil.generatedList().stream()
                .collect(Collectors.toMap(Meal::getId, meal -> meal)));
        counter.set(meals.size());
    }

    private Long getGenerateId() {
        return counter.incrementAndGet();
    }

    @Override
    public Meal getMealById(Long mealId) {
        Meal meal = meals.get(mealId);
        if (meal == null) {
            throw new RuntimeException("Meal with id " + mealId + " not found");
        }
        return meal;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void create(Meal meal) {
        Long id = getGenerateId();
        meals.put(id,
                new Meal(id,
                        meal.getDateTime(),
                        meal.getDescription(),
                        meal.getCalories()));
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(Long idMeal) {
        meals.remove(idMeal);
    }
}

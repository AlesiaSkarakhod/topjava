package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class MealDaoImpl implements MealDao {

    private final  Map<Long, Meal> meals;
    private final  LongAdder counter = new LongAdder();
    private static MealDaoImpl mealDaoImpl;

    public static MealDaoImpl getInstance() {
        if (mealDaoImpl == null) {
            mealDaoImpl = new MealDaoImpl();
        }
        return mealDaoImpl;
    }

    private MealDaoImpl() {
        meals = new ConcurrentHashMap<>(MealsUtil.generateMap());
    }

    @Override
    public Meal getMealById(Long mealId) {
        Meal meal = meals.get(mealId);
        if (meal == null) {
            throw new RuntimeException("Meal with id " + mealId +" not found");
        }
        return meal;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void create(Meal meal) {
        counter.increment();
        meals.put(counter.sum(),
                new Meal(counter.sum(),
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

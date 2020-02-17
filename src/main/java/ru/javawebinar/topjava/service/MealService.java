package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, Integer userId) {
        log.info("create: " + meal + " with userId= " + userId);
        return repository.save(meal, userId);
    }

    public void delete(int mealId, Integer userId) {
        log.info("delete meal with id= " + mealId + " with userId= " + userId);
        checkNotFoundWithId(repository.delete(mealId, userId), mealId);
    }

    public Meal getOneMeal(int mealId, Integer userId) {
        log.info("get meal with id= " + mealId + " with userId= " + userId);
        return checkNotFoundWithId(repository.get(mealId, userId), mealId);
    }

    public List<MealTo> findAll(Integer userId) {
        log.info("getAll with userId= " + userId);
        return MealsUtil.getTos(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void update(Meal meal, Integer userId) {
        log.info("update meal:  " + meal + " with userId= " + userId);
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

}
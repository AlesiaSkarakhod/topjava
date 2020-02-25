package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = mealService.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(mealService.get(newId, USER_ID), newMeal);
    }

    @Test
    public void get() throws Exception {
        Meal meal = mealService.get(MEAL_USER_3.getId(), USER_ID);
        assertMatch(meal, MEAL_USER_3);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        mealService.get(100, USER_ID);
    }

    @Test
    public void update() throws Exception {
        Meal updated = getUpdated();
        mealService.update(updated, USER_ID);
        assertMatch(mealService.get(updated.getId(), USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Meal updated = getUpdated();
        updated.setId(100);
        mealService.update(updated, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        mealService.delete(MEAL_USER_3.getId(), USER_ID);
        mealService.get(MEAL_USER_3.getId(), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        mealService.delete(100, USER_ID);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> mealList = mealService.getAll(USER_ID);
        assertMatch(mealList, MEAL_USER_1, MEAL_USER_2, MEAL_USER_3);
    }
}

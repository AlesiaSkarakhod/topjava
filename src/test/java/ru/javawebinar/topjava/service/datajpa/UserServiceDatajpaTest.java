package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserBaseServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(DATAJPA)
public class UserServiceDatajpaTest extends UserBaseServiceTest {

    @Test(expected = NotFoundException.class)
    public void getUserMealsNotFound() throws Exception {
        service.findUserWithMealByUserId(1);
    }

    @Test
    public void findUserWithMeals() throws Exception {
        User user = service.findUserWithMealByUserId(USER_ID);
        MEAL_MATCHER.assertMatch(user.getMeals(), List.of(MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1));
    }

}

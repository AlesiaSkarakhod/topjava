package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDateTime.of;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal MEAL_USER_1 = new Meal(START_SEQ + 2, of(2020, Month.FEBRUARY, 22, 8, 10), "breakfast", 500);
    public static final Meal MEAL_USER_2 = new Meal(START_SEQ + 3, of(2020, Month.FEBRUARY, 23, 13, 0), "lunch", 700);
    public static final Meal MEAL_USER_3 = new Meal(START_SEQ + 4, of(2020, Month.FEBRUARY, 24, 20, 20), "dinner", 350);
    public static final Meal MEAL_ADMIN_4 = new Meal(START_SEQ + 5, of(2020, Month.FEBRUARY, 22, 9, 10), "breakfast", 1000);
    public static final Meal MEAL_ADMIN_1 = new Meal(START_SEQ + 6, of(2020, Month.FEBRUARY, 23, 13, 20), "lunch", 700);
    public static final Meal MEAL_ADMIN_2 = new Meal(START_SEQ + 7, of(2020, Month.FEBRUARY, 24, 16, 10), "dinner", 500);
    public static final Meal MEAL_ADMIN_3 = new Meal(START_SEQ + 8, of(2020, Month.FEBRUARY, 24, 22, 15), "evening meal", 150);

    public static Meal getNew() {
        return new Meal(null, of(2020, Month.APRIL, 20, 9, 20), "Second breakfast", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_USER_1);
        updated.setDescription("New description");
        updated.setCalories(1800);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().containsExactlyElementsOf(expected);
    }
}

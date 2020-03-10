package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealBaseServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class MealBaseServiceDatajpaTest extends MealBaseServiceTest {

}

package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.DateTimeUtil.isBetweenDateTime;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public void create(Meal meal) {
        log.info("create meal (MealRestController): " + meal);
        checkNew(meal);
        service.create(meal, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("create meal (MealRestController): " + meal);
        assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }


    public void delete(int mealId){
        log.info("delete (MealRestController) meal with mealId= " + mealId + ", userId= " + authUserId());
        service.delete(mealId, authUserId());
    }

    public Meal get(int mealId) {
        log.info("get (MealRestController) meal with mealId= " + mealId + ", userId= " + authUserId());
        return service.getOneMeal(mealId, authUserId());
    }

    public List<MealTo> findAll()  {
        log.info("(MealRestController) getAll with userId= " + authUserId());
        return service.findAll(authUserId());
    }

    public List<MealTo> getFiltered(String startDate, String endDate, String startTime, String endTime) {
        log.info("get filtered meal");
        LocalDate sDate = startDate == null ? LocalDate.MIN : startDate.equals("") ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate eDate = endDate == null ? LocalDate.MAX : endDate.equals("") ? LocalDate.MAX : LocalDate.parse(endDate);
        LocalTime sTime = startTime == null ? LocalTime.MIN : startTime.equals("") ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime eTime = endTime == null ? LocalTime.MAX : endTime.equals("") ? LocalTime.MAX : LocalTime.parse(endTime);
        return findAll().stream()
                .filter(meal -> isBetweenDateTime(meal.getDateTime(), sDate, eDate, sTime, eTime))
                .collect(Collectors.toList());
    }

}
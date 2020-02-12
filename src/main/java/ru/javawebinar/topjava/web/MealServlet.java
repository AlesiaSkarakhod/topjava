package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String CHANGE_MEAL = "/changeMeal.jsp";
    private static final String LIST_MEALS = "/meals.jsp";

    private MealDao mealDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("Init MealServlet");
        super.init(config);
        mealDao = new MealDaoImpl();
    }

    private Long getId(HttpServletRequest req) {
        return Long.parseLong(req.getParameter("Id"));
    }

    private List<MealTo> filteredMeals() {
        return MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug("redirect to meals.jsp");
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            req.setAttribute("dateTimeFormatter", dateTimeFormatter);
            req.setAttribute("listMealsDynamic", filteredMeals());
            req.getRequestDispatcher(LIST_MEALS).forward(req, resp);
            return;
        }

        switch (action) {
            case "delete":
                mealDao.delete(getId(req));
                resp.sendRedirect("meals");
                break;
            case "change":
                Meal meal = mealDao.getMealById(getId(req));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher(CHANGE_MEAL).forward(req, resp);
                break;
            case "create":
                Meal mealNew = new Meal();
                req.setAttribute("meal", mealNew);
                req.getRequestDispatcher(CHANGE_MEAL).forward(req, resp);
                break;
            default:
                resp.sendRedirect("meals");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug("redirect to change.jsp");
        req.setCharacterEncoding("UTF-8");

        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        String idParam = req.getParameter("changeForm");
        if (idParam.isEmpty()) {
            mealDao.create(new Meal(0L, dateTime, description, calories));
        } else {
            long id = Long.parseLong(idParam);
            mealDao.update(new Meal(id, dateTime, description, calories));
        }
        resp.sendRedirect("meals");
    }
}

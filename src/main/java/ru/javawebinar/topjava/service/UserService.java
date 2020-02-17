package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        log.info("create user: " + user);
        return repository.save(user);
    }

    public void delete(int id) {
        log.info("delete user with id= " + id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        log.info("get user with id= " + id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        log.info("get user with Email= " + email);
        return checkNotFound(repository.getByEmail(email), "email= " + email);
    }

    public List<User> getAll() {
        log.info("get all users: ");
        return repository.getAll();
    }

    public void update(User user) {
        log.info("update user: " + user);
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}
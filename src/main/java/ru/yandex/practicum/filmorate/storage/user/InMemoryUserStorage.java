package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {
    private long idCounter = 0;
    private Map<Long, User> users = new HashMap<>();

    public User addUser(@Valid User user) {
        user.setId(getNewId());
        if (user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser (@Valid User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return user;
        } else {
            throw new NoSuchElementException("User update fail! ID " + user.getId() + " not found.");
        }
    }
    public User getUser(Long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            throw new NoSuchElementException("User " + id + " not found");
        }
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    private long getNewId() {
        return ++idCounter;
    }

}

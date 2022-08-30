package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.*;

@Slf4j
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
        log.info("User created. ID {}", user.getId());
        return user;
    }

    public Optional<User> updateUser (@Valid User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.info("User updated. ID {}", user.getId());
            return Optional.of(user);
        } else {
            log.error("User update fail! ID {} not found.", user.getId());
            return Optional.empty();
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

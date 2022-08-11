package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private long idCounter = 0;
    private Map<Long, User> users = new HashMap<>();

    @PostMapping
    public HttpEntity<User> addUser(@Valid @RequestBody User user) {
        user.setId(getNewId());
        if (user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);
        log.info("User created. ID {}", user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping
    public HttpEntity<User> updateUser (@Valid @RequestBody User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.info("User updated. ID {}", user.getId());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            log.error("User update fail! ID {} not found.", user.getId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    private long getNewId() {
        return ++idCounter;
    }

}

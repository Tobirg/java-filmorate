package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {

    User addUser(User user);

    Optional<User> updateUser (User user);

    User getUser(Long id);

    List<User> getUsers();
}

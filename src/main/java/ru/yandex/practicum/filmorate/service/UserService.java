package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.*;

@Service
@Slf4j
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addFriend(Long userId, Long friendId) {
        User user1 = userStorage.getUser(userId);
        User user2 = userStorage.getUser(friendId);
        user1.getFriends().add(friendId);
        user2.getFriends().add(userId);
        log.info("User {} add friend {}", userId, friendId);
    }

    public void deleteFriend(Long userId, Long friendId) {
        User user1 = userStorage.getUser(userId);
        User user2 = userStorage.getUser(friendId);
        user1.getFriends().remove(friendId);
        user2.getFriends().remove(userId);
        log.info("User {} delete friend {}", userId, friendId);
    }

    public List<User> getFriendsList(Long userId) {
        List<User> list = new ArrayList<>();
        Set<Long> friendsSet = userStorage.getUser(userId).getFriends();
        for (Long user : friendsSet) {
            list.add(userStorage.getUser(user));
        }
        return list;
    }

    public List<User> getCommonFriends(Long id, Long otherId) {
        Set<Long> user1 = userStorage.getUser(id).getFriends();
        Set<Long> user2 = userStorage.getUser(otherId).getFriends();

        List<User> commonFriends = new ArrayList<>();
        for (Long userId : user1) {
            if (user2.contains(userId)) {
                commonFriends.add(userStorage.getUser(userId));
            }
        }
        return commonFriends;
    }
}

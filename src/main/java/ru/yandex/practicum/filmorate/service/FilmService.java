package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmService {

    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public void putLike(Long id, Long userId) {
        filmStorage.getFilm(id).getLikes().add(userId);
    }

    public void deleteLike(Long id, Long userId) {
        if (!filmStorage.getFilm(id).getLikes().remove(userId)) {
            throw new NoSuchElementException("Like deletion failed. User " + userId + " not found");
        }
    }

    public List<Film> getPopularFilms(Integer count) {
        List<Film> listOfPopular = List.copyOf(filmStorage.getFilms());
        return listOfPopular.stream()
                .sorted(Comparator.comparingInt((Film f) -> f.getLikes().size()).reversed())
                .limit(count)
                .collect(Collectors.toList());

    }
}

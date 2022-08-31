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

    public Film addFilm(Film film) {
        Film tempFilm = filmStorage.addFilm(film);
        log.info("Film created. ID {}", tempFilm.getId());
        return tempFilm;
    }

    public Film updateFilm(Film film) {
        log.info("Film updated. ID {}", film.getId());
        return filmStorage.updateFilm(film);
    }

    public List<Film> getFilms() {
        log.info("Films list get request");
        return filmStorage.getFilms();
    }

    public Film getFilm(Long id) {
        log.info("Film {} get request", id);
        return filmStorage.getFilm(id);
    }

    public void putLike(Long id, Long userId) {
        filmStorage.getFilm(id).getLikes().add(userId);
        log.info("Like to film {} from user {} added", id, userId);
    }

    public void deleteLike(Long id, Long userId) {
        if (!filmStorage.getFilm(id).getLikes().remove(userId)) {
            throw new NoSuchElementException("Like deletion failed. User " + userId + " not found");
        }
        log.info("Like to film {} from user {} removed", id, userId);
    }

    public List<Film> getPopularFilms(Integer count) {
        List<Film> listOfPopular = List.copyOf(filmStorage.getFilms());
        return listOfPopular.stream()
                .sorted(Comparator.comparingInt((Film f) -> f.getLikes().size()).reversed())
                .limit(count)
                .collect(Collectors.toList());

    }
}

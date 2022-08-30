package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private long idCounter = 0;
    private Map<Long, Film> films = new HashMap<>();

    public Film addFilm(@Valid Film film) {
        film.setId(getNewId());
        films.put(film.getId(), film);
        log.info("Film created. ID {}", film.getId());
        return film;
    }

    public Film updateFilm(@Valid Film film) {
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.info("Film updated. ID {}", film.getId());
            return film;
        } else {
            throw new NoSuchElementException("Film update fail! ID " + film.getId() + " not found.");
        }
    }

    public Film getFilm(Long id) {
        if (films.containsKey(id)) {
            return films.get(id);
        } else {
            throw new NoSuchElementException("Film " + id + " not found");
        }
    }

    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    private long getNewId() {
        return ++idCounter;
    }
}

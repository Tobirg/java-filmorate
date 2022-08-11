package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private long idCounter = 0;
    private Map<Long, Film> films = new HashMap<>();

    @PostMapping
    public HttpEntity<Film> addFilm(@Valid @RequestBody Film film) {
            film.setId(getNewId());
            films.put(film.getId(), film);
            log.info("Film created. ID {}", film.getId());
            return ResponseEntity.status(HttpStatus.OK).body(film);
    }

    @PutMapping
    public HttpEntity<Film> updateFilm(@Valid @RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.info("Film updated. ID {}", film.getId());
            return ResponseEntity.status(HttpStatus.OK).body(film);
        } else {
            log.error("Film update fail! ID {} not found.", film.getId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    private long getNewId() {
        return ++idCounter;
    }
}

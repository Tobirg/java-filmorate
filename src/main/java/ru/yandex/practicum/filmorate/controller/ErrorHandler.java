package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import java.util.Map;
import java.util.NoSuchElementException;
@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> validationException(final ValidationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(Map.of("ValidationError", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> notFoundException(final NoSuchElementException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(Map.of("NotFound error", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> otherException(final Throwable e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

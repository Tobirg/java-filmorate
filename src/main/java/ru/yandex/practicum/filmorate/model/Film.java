package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.yandex.practicum.filmorate.validators.ReleaseDate;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class Film {
    private long id;
    @NotNull
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    @ReleaseDate
    private LocalDate releaseDate;
    @Positive
    private int duration;
}

package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private long id;
    @NotNull
    @Email
    private String email;
    @NotBlank
    @NotNull
    @Pattern(regexp = "\\S*")
    private String login;
    private String name;
    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    private Set<Long> friends = new HashSet<>();
}

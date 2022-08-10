package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class User {
    private int id;
    @NotNull
    @Email
    private String email;
    @NotBlank
    @NotNull
    private String login;
    private String name;
    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
}

package ru.hogwarts.school.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Что-то не так")
public class BadRequestExeption extends RuntimeException {
    public BadRequestExeption() {
        super();
    }
}


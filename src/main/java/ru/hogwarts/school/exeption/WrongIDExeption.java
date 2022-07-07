package ru.hogwarts.school.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Такого ID нет")
public class WrongIDExeption extends RuntimeException {
        public WrongIDExeption() {
            super();
        }
    }


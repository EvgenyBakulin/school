package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.WrongIDExeption;
import ru.hogwarts.school.interfac.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {

        this.facultyRepository = facultyRepository;
    }

    public Collection<Faculty> getHogwarts() {
        logger.info("Вызван метод getHogwarts");
        return facultyRepository.findAll();
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Вызван метод addFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long myId) {
        logger.info("Вызван метод getFaculty");
        return facultyRepository.findById(myId).orElseThrow(() -> {
            logger.error("Нет факультета с id " + myId);
            throw new WrongIDExeption();
        });
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Вызван метод updateFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long myId) {
        logger.info("Вызван метод deleteFaculty");
        if (facultyRepository.existsById(myId)) {
            facultyRepository.deleteById(myId);
        } else {
            logger.error("Нет факультета с id " + myId);
            throw new WrongIDExeption();
        }
    }

    public Collection<Faculty> toNameOrColor(String name, String color) {
        logger.info("Вызван метод toNameOrColor");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Collection<Student> getAllFacultyStudents(long id) {
        logger.info("Вызван метод getFacultyStudents");
        return facultyRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Нет факультета с id " + id);
                    throw new WrongIDExeption();
                })
                .getStudents();
    }

    /*Новый метод поиска самого длинного имени факультета.
    * Способов, кстати, может быть немсколько,я выбрал тот, который показался лучше*/
    public String longestFacultyName() {
        return facultyRepository.findAll()
                .stream()
                .reduce((x, y) -> {
                    if (x.getName().length() > y.getName().length()) {
                        return x;
                    } else {
                        return y;
                    }
                }).get().getName();

    }
}
